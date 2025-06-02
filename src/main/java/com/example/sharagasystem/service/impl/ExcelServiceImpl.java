package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.ExcelProcessingException;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.dto.excel.ExcelExportResidentDetailsPreferences;
import com.example.sharagasystem.model.dto.excel.ExcelImportResidentDto;
import com.example.sharagasystem.model.dto.excel.ExcelResidentColumnMapping;
import com.example.sharagasystem.model.dto.response.ResidentDetailsLowInfoResponseDto;
import com.example.sharagasystem.security.service.AuthenticationService;
import com.example.sharagasystem.security.service.UserService;
import com.example.sharagasystem.service.DormitoryService;
import com.example.sharagasystem.service.EmailService;
import com.example.sharagasystem.service.ExcelService;
import com.example.sharagasystem.service.ResidentService;
import com.example.sharagasystem.service.RoomService;
import com.example.sharagasystem.utility.ColumnInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private static final Integer MAX_ITEMS_TO_WAIT_THE_RESPONSE = 500;
    private final ApplicationContext applicationContext;
    private final EmailService emailService;
    private final DormitoryService dormitoryService;
    private final ResidentService residentService;
    private final UserService userService;
    private final RoomService roomService;
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<?> generateResidentExcel(ExcelExportResidentDetailsPreferences preferences) {
        Pageable page = PageRequest.of(0, Integer.MAX_VALUE);
        JpaSort jpaSort = JpaSort.unsafe(Sort.Direction.DESC, "lastName");
        Page<ResidentDetailsLowInfoResponseDto> residents = residentService.getResidentsByDormitory(preferences.getDormitoryId(), preferences.getTextToSearch(), page);
        Workbook workbook = generateExcel(BigDecimal.valueOf(residents.getTotalElements()).intValue(), preferences, ExcelResidentColumnMapping.columnMap,
                pageable -> residentService.getResidentsByDormitory(preferences.getDormitoryId(), preferences.getTextToSearch(), pageable), jpaSort, "Жителі");

        if(workbook != null) {
            return createExcelFileResponse(workbook);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate the Excel file.");
    }

    @Override
    @Transactional
    public void registerResidents(MultipartFile file, UUID dormitoryId) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);

        List<ExcelImportResidentDto>  excelImportResidentDtos = ExcelParserServiceImpl.parseExcelToMaterials(file, ExcelImportResidentDto.class);
        List<ResidentDetails> residentDetails = new ArrayList<>();
        
        excelImportResidentDtos.forEach(item -> {
            residentDetails.add(authenticationService.registerResidentByExcel(item, dormitory));
        });

        residentService.saveAll(residentDetails);
    }

    public <T> Workbook generateExcel(Integer itemsCount,
                                      ExcelExportResidentDetailsPreferences preferences,
                                      Map<String, ColumnInfo<T>> columnMap,
                                      Function<Pageable, Page<T>> fetchFunction,
                                      Sort sortBy,
                                      String sheetName) {
        if (itemsCount > MAX_ITEMS_TO_WAIT_THE_RESPONSE) {
            final ExcelServiceImpl proxy = applicationContext.getBean(ExcelServiceImpl.class);
            proxy.sendExcelEmail(preferences, columnMap, fetchFunction, sortBy);
            return null;
        }
        return generateExcelWorkbook(preferences, columnMap, fetchFunction, sortBy, sheetName);
    }

    private <T> Workbook generateExcelWorkbook(final ExcelExportResidentDetailsPreferences preferences,
                                               final Map<String, ColumnInfo<T>> columnMap,
                                               final Function<Pageable, Page<T>> fetchFunction,
                                               final Sort sortBy,
                                               String sheetName) {
        final var workbook = new XSSFWorkbook();
        final XSSFSheet sheet = workbook.createSheet(sheetName);
        final XSSFRow headerRow = sheet.createRow(0);

        final List<String> columnsToInclude = preferences.getColumnsToInclude();
        final List<ColumnInfo<T>> columns = columnsToInclude.stream()
                .map(columnMap::get)
                .filter(Objects::nonNull)
                .toList();

        for (int i = 0; i < columns.size(); i++) {
            headerRow.createCell(i).setCellValue(columns.get(i).getHeader());
        }
        setHeaderRowStyle(workbook, headerRow);
        setDefaultColumnWidths(sheet);

        Page<T> itemsPage;
        Pageable pageable = PageRequest.of(0, 200, sortBy);
        final AtomicInteger rowCounter = new AtomicInteger(1);
        do {
            itemsPage = fetchFunction.apply(pageable);
            for (final T item : itemsPage) {
                final XSSFRow dataRow = sheet.createRow(rowCounter.getAndIncrement());
                for (int i = 0; i < columns.size(); i++) {
                    final ColumnInfo<T> column = columns.get(i);
                    final String cellValue = column.getDataExtractor().apply(item);
                    dataRow.createCell(i).setCellValue(cellValue != null ? cellValue : "");
                }
            }
            pageable = pageable.next();
        } while (itemsPage.hasNext());
        return workbook;
    }

    private ResponseEntity<?> createExcelFileResponse(Workbook workbook) {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
            throw new ExcelProcessingException("Failed to write workbook to the output stream ", e);
        }
        final byte[] content = bos.toByteArray();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
        final InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Materials_Export.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(content.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }

    @Async
    @Transactional(readOnly = true)
    public <T> void sendExcelEmail(final ExcelExportResidentDetailsPreferences preferences,
                                   final Map<String, ColumnInfo<T>> columnMap,
                                   final Function<Pageable, Page<T>> fetchFunction,
                                   final Sort sortBy) {
        try (final Workbook workbook = generateExcelWorkbook(preferences, columnMap, fetchFunction, sortBy, "excel")) {

            File file = File.createTempFile("file", ".xlsx");
            final FileOutputStream bos = new FileOutputStream (file);
            workbook.write(bos);

            emailService.sendHtmlEmail(preferences.getEmailToSend(), "fgrg", "egrg", "file", file);
        } catch (Exception e) {
            log.error("Error occurred when generating excel file and sending it to email!", e);
        }
    }

    private void setHeaderRowStyle(final Workbook workbook, final Row headerRow) {
        final var style = workbook.createCellStyle();
        final var font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        for (final var cell : headerRow) {
            cell.setCellStyle(style);
        }
    }

    private void setDefaultColumnWidths(final Sheet sheet) {
        setColumnWidth(sheet, 0, 50);
        setColumnWidth(sheet, 1, 20);
        setColumnWidth(sheet, 2, 20);
        setColumnWidth(sheet, 3, 15);
        setColumnWidth(sheet, 4, 20);
        setColumnWidth(sheet, 5, 30);
        setColumnWidth(sheet, 6, 20);
        setColumnWidth(sheet, 7, 15);
        setColumnWidth(sheet, 8, 15);
        setColumnWidth(sheet, 9, 15);
        setColumnWidth(sheet, 10, 15);
        setColumnWidth(sheet, 11, 15);
        setColumnWidth(sheet, 12, 30);
        setColumnWidth(sheet, 13, 30);
        setColumnWidth(sheet, 14, 30);
        setColumnWidth(sheet, 15, 20);
        setColumnWidth(sheet, 16, 15);
        setColumnWidth(sheet, 17, 15);
        setColumnWidth(sheet, 18, 20);
        setColumnWidth(sheet, 19, 20);
    }

    private void setColumnWidth(Sheet sheet, int columnIndex, int width) {
        sheet.setColumnWidth(columnIndex, width * 256);
    }
}
