package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.springframework.stereotype.Component;

/**
 * Rule:
 * 10 points if the time of purchase is after 2:00pm and before 4:00pm.
 */
@Component
public class PurchaseTimePointsHandler implements PointsCalcHandler {

    @Override
    public int calculatePoints(Receipt receipt) {
        var hour = receipt.getPurchaseTime().getHour();
        return (hour >= 14 && hour < 16) ? 10 : 0;
    }
}
