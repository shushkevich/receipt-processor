package com.github.shushkevich.receiptprocessor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ReceiptReq {

    @NotBlank
    @Pattern(regexp = "[\\w\\s\\-&]+")
    private String retailer;

    @NotNull
    private LocalDate purchaseDate;

    @NotNull
    private LocalTime purchaseTime;

    @NotNull
    @Pattern(regexp = "\\d+\\.\\d{2}")
    private String total;

    @NotEmpty
    private List<ReceiptItemReq> items;
}
