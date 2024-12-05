package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Rule:
 * 25 points if the total is a multiple of 0.25.
 */
@Component
public class QuarterTotalPointsHandler implements PointsCalcHandler {

    @Override
    public int calculatePoints(Receipt receipt) {
        return receipt.getTotal().remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0 ? 25 : 0;
    }
}
