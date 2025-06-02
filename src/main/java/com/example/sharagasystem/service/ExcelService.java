package com.example.sharagasystem.service;

import com.example.sharagasystem.model.dto.excel.ExcelExportResidentDetailsPreferences;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    ResponseEntity<?> generateResidentExcel(ExcelExportResidentDetailsPreferences preferences);

    void registerResidents(MultipartFile file, UUID dormitoryId);
}
