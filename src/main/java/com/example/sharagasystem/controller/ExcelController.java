//package com.example.sharagasystem.controller;
//
//
//import com.example.sharagasystem.service.ResidentService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequestMapping("/excel")
//@RequiredArgsConstructor
//public class ExcelController {
//    private final ExcelService excelService;
//    private final ResidentService residentService;
//    @PostMapping("/residents")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public ResponseEntity<?> generateResidentExcel() {
//        log.info("Entering POST /excel/residents");
//        return excelService.generateResidentExcel();
//    }
//}
