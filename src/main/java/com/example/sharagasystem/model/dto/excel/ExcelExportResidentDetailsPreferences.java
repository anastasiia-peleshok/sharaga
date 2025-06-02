package com.example.sharagasystem.model.dto.excel;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelExportResidentDetailsPreferences {
    private List<String> columnsToInclude;
    private UUID dormitoryId;
    private String textToSearch;
    private String emailToSend;
}
