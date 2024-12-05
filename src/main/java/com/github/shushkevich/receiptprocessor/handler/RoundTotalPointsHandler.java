package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Rule:
 * 50 points if the total is a round dollar amount with no cents.
 */
@Component
public class RoundTotalPointsHandler implements PointsCalcHandler {

    @Override
    public int calculatePoints(Receipt receipt) {
        return receipt.getTotal().remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0 ? 50 : 0;
    }
}
