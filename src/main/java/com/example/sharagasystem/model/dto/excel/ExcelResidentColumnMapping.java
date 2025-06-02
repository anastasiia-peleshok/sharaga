package com.example.sharagasystem.model.dto.excel;

import com.example.sharagasystem.model.dto.response.ResidentDetailsLowInfoResponseDto;
import com.example.sharagasystem.utility.ColumnInfo;
import java.util.HashMap;
import java.util.Map;

public class ExcelResidentColumnMapping {
    public static final String TABLE_NAME = "Жителі";
    public static final String ID = "ID";
    public static final String FULL_NAME_COLUMN = "ПІБ";
    public static final String ROOM_COLUMN = "Кімната №";
    public static final String YEARS_UNIVERSITY_COLUMN = "Курс";
    public static final String DEBT_COLUMN = "Заборгованість";
    public static final String PENALTY_POINT_COLUMN = "Штрафні бали";

    public static final String EMPTY_STRING = "";

    public static final Map<String, ColumnInfo<ResidentDetailsLowInfoResponseDto>> columnMap = new HashMap<>();

    static {
        columnMap.put("ID", new ColumnInfo<>(ID, response -> response.getId() != null ? response.getId().toString() : EMPTY_STRING));
        columnMap.put("FULL_NAME_COLUMN", new ColumnInfo<>(FULL_NAME_COLUMN, ExcelResidentColumnMapping::setFullName));
        columnMap.put("ROOM_COLUMN", new ColumnInfo<>(ROOM_COLUMN, response -> response.getRoom() != null ? response.getRoom().toString() : EMPTY_STRING));
        columnMap.put("YEARS_UNIVENSITY_COLUMN", new ColumnInfo<>(YEARS_UNIVERSITY_COLUMN, response -> response.getYearsInUniversity() != null ? response.getYearsInUniversity().toString() : EMPTY_STRING));
        columnMap.put("DEBT_COLUMN", new ColumnInfo<>(DEBT_COLUMN, response -> response.getDebt() != null ? response.getDebt().toString() : EMPTY_STRING));
        columnMap.put("PENALTY_POINT_COLUMN", new ColumnInfo<>(PENALTY_POINT_COLUMN, response -> response.getPenaltyPoints() != null ? response.getPenaltyPoints().toString() : EMPTY_STRING));
    }

    private static String setFullName(ResidentDetailsLowInfoResponseDto responseDto) {
        if(responseDto != null && responseDto.getLastName() != null && responseDto.getFirstName() != null) {
            return responseDto.getLastName() + " " + responseDto.getFirstName();
        }
        return new String();
    }

}
