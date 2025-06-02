package com.example.sharagasystem.controller;


import com.example.sharagasystem.model.dto.excel.ExcelExportResidentDetailsPreferences;
import com.example.sharagasystem.service.ExcelService;
import com.example.sharagasystem.service.ResidentService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {
    private final ExcelService excelService;
    private final ResidentService residentService;

    @PostMapping("/residents")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> generateResidentExcel(
            @RequestBody ExcelExportResidentDetailsPreferences preferences
            ) {
        log.info("Entering POST /excel/residents");
        List<String> columnsToInclude = new ArrayList<>();
        columnsToInclude.add("ID");
        columnsToInclude.add("FULL_NAME_COLUMN");
        columnsToInclude.add("YEARS_UNIVENSITY_COLUMN");
        columnsToInclude.add("DEBT_COLUMN");
        columnsToInclude.add("PENALTY_POINT_COLUMN");
        preferences.setColumnsToInclude(columnsToInclude);
        return excelService.generateResidentExcel(preferences);
    }

    @PostMapping("/import/dormitory/{dormitoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void importMaterialDetails(@RequestPart MultipartFile file, @PathVariable UUID dormitoryId) {
        log.info("Entering POST/excel/import/dormitory/{}", dormitoryId);
        excelService.registerResidents(file, dormitoryId);
    }
}
