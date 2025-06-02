package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.UnableToProcessDataFromExcelException;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class ExcelParserServiceImpl {
    public static <T> List<T> parseExcelToMaterials(MultipartFile multipartFile, Class<T> clazz) {
        try {
            return Poiji.fromExcel(multipartFile.getInputStream(), getExcelFileType(multipartFile), clazz).stream().toList();
        } catch (IOException e) {
            throw new UnableToProcessDataFromExcelException("Can't parse data from excel file " + multipartFile.getOriginalFilename() + e);
        }
    }

    public static PoijiExcelType getExcelFileType(MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String fileExtension = FilenameUtils.getExtension(fileName).toLowerCase();
        return fileExtension.equals("xlsx") ?  PoijiExcelType.XLSX : PoijiExcelType.XLS;
    }
}
