package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Rule:
 * If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer.
 * The result is the number of points earned.
 */
@Component
public class ItemDescriptionPointsHandler implements PointsCalcHandler {

    @Override
    public int calculatePoints(Receipt receipt) {
        return receipt.getItems().stream()
                .mapToInt(item ->
                        item.getShortDescription().trim().length() % 3 == 0 ?
                                item.getPrice().multiply(new BigDecimal("0.2")).setScale(0, RoundingMode.UP).intValue() : 0
                )
                .sum();
    }
}
