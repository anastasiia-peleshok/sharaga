package com.example.sharagasystem.model.dto.excel;

import com.poiji.annotation.ExcelCellName;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ExcelImportResidentDto {
    @ExcelCellName("firstName")
    private String firstName;
    @ExcelCellName("lastName")
    private String lastName;
    @ExcelCellName("email")
    private String email;
    @ExcelCellName("password")
    private String password;
    @ExcelCellName("phone")
    private String phone;
    @ExcelCellName("roomNumber")
    private String roomNumber;
    @ExcelCellName("birthDate")
    private LocalDate birthDate;
    @ExcelCellName("gender")
    private String gender;
    @ExcelCellName("dateOfEntry")
    private LocalDate dateOfEntry;

}
