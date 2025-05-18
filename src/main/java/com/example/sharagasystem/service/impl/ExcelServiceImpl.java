//package com.example.sharagasystem.service.impl;
//
//import com.example.sharagasystem.service.ExcelService;
//import com.example.sharagasystem.utility.ColumnInfo;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.function.Function;
//import lombok.RequiredArgsConstructor;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.VerticalAlignment;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ExcelServiceImpl implements ExcelService {
//
//    @Override
//    public ResponseEntity<?> generateResidentExcel() {
//        return null;
//    }
//
//    public <T> Workbook generateExcel(final Integer itemsCount,
//                                      final ExcelExportPreferences preferences,
//                                      final Map<String, ColumnInfo<T>> columnMap,
//                                      final Function<Pageable, Page<T>> fetchFunction,
//                                      final Sort sortBy,
//                                      final FailedFileUploadEmailData failedFileUploadEmailData,
//                                      String sheetName) {
//        if (itemsCount > MAX_ITEMS_TO_WAIT_THE_RESPONSE) {
//            final ExcelServiceImpl proxy = applicationContext.getBean(ExcelServiceImpl.class);
//            proxy.sendExcelEmail(preferences, columnMap, fetchFunction, sortBy, failedFileUploadEmailData);
//            return null;
//        }
//        return generateExcelWorkbook(preferences, columnMap, fetchFunction, sortBy, sheetName);
//    }
//
//    private <T> Workbook generateExcelWorkbook(final ExcelExportPreferences preferences,
//                                               final Map<String, ColumnInfo<T>> columnMap,
//                                               final Function<Pageable, Page<T>> fetchFunction,
//                                               final Sort sortBy,
//                                               String sheetName) {
//        final var workbook = new XSSFWorkbook();
//        final XSSFSheet sheet = workbook.createSheet(sheetName);
//        final XSSFRow headerRow = sheet.createRow(0);
//
//        final List<String> columnsToInclude = preferences.getColumnsToInclude();
//        final List<ColumnInfo<T>> columns = columnsToInclude.stream()
//                .map(columnMap::get)
//                .filter(Objects::nonNull)
//                .toList();
//
//        for (int i = 0; i < columns.size(); i++) {
//            headerRow.createCell(i).setCellValue(columns.get(i).getHeader());
//        }
//        setHeaderRowStyle(workbook, headerRow);
//        setDefaultColumnWidths(sheet);
//
//        Page<T> itemsPage;
//        Pageable pageable = PageRequest.of(0, 200, sortBy);
//        final AtomicInteger rowCounter = new AtomicInteger(1);
//        do {
//            itemsPage = fetchFunction.apply(pageable);
//            for (final T item : itemsPage) {
//                final XSSFRow dataRow = sheet.createRow(rowCounter.getAndIncrement());
//                for (int i = 0; i < columns.size(); i++) {
//                    final ColumnInfo<T> column = columns.get(i);
//                    final String cellValue = column.getDataExtractor().apply(item);
//                    dataRow.createCell(i).setCellValue(cellValue != null ? cellValue : "");
//                }
//            }
//            pageable = pageable.next();
//        } while (itemsPage.hasNext());
//        return workbook;
//    }
//
//    private void setHeaderRowStyle(final Workbook workbook, final Row headerRow) {
//        final var style = workbook.createCellStyle();
//        final var font = workbook.createFont();
//        font.setBold(true);
//        style.setFont(font);
//        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        for (final var cell : headerRow) {
//            cell.setCellStyle(style);
//        }
//    }
//
//    private void setDefaultColumnWidths(final Sheet sheet) {
//        setColumnWidth(sheet, 0, 50);
//        setColumnWidth(sheet, 1, 20);
//        setColumnWidth(sheet, 2, 20);
//        setColumnWidth(sheet, 3, 15);
//        setColumnWidth(sheet, 4, 20);
//        setColumnWidth(sheet, 5, 30);
//        setColumnWidth(sheet, 6, 20);
//        setColumnWidth(sheet, 7, 15);
//        setColumnWidth(sheet, 8, 15);
//        setColumnWidth(sheet, 9, 15);
//        setColumnWidth(sheet, 10, 15);
//        setColumnWidth(sheet, 11, 15);
//        setColumnWidth(sheet, 12, 30);
//        setColumnWidth(sheet, 13, 30);
//        setColumnWidth(sheet, 14, 30);
//        setColumnWidth(sheet, 15, 20);
//        setColumnWidth(sheet, 16, 15);
//        setColumnWidth(sheet, 17, 15);
//        setColumnWidth(sheet, 18, 20);
//        setColumnWidth(sheet, 19, 20);
//    }
//
//    private void setColumnWidth(Sheet sheet, int columnIndex, int width) {
//        sheet.setColumnWidth(columnIndex, width * 256);
//    }
//}
