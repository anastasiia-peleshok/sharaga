package com.example.sharagasystem.model.dto.request.furniture;

import com.example.sharagasystem.model.FurnitureType;
import com.example.sharagasystem.model.enums.StatusType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FurnitureRequestDto {
    private String name;
    @NotBlank(message = "cannot be null or empty")
    private String itemNumber;
    @NotNull(message = "cannot be null")
    private FurnitureType type;
    @NotNull(message = "cannot be null")
    private StatusType status;
    private Double price;

    private UUID dormitoryId;
}
