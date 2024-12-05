package com.github.shushkevich.receiptprocessor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReceiptItemReq {

    @NotBlank
    @Pattern(regexp = "[\\w\\s\\-]+")
    private String shortDescription;

    @NotNull
    @Pattern(regexp = "\\d+\\.\\d{2}")
    private String price;
}
