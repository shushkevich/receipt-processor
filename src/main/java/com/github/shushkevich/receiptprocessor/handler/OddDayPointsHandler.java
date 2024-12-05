package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.springframework.stereotype.Component;

/**
 * Rule:
 * 6 points if the day in the purchase date is odd.
 */
@Component
public class OddDayPointsHandler implements PointsCalcHandler {

    @Override
    public int calculatePoints(Receipt receipt) {
        return receipt.getPurchaseDate().getDayOfMonth() % 2 != 0 ? 6 : 0;
    }
}
