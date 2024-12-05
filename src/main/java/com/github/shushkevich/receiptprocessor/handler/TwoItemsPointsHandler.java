package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.springframework.stereotype.Component;

/**
 * Rule:
 * 5 points for every two items on the receipt.
 */
@Component
public class TwoItemsPointsHandler implements PointsCalcHandler {

    @Override
    public int calculatePoints(Receipt receipt) {
        return (receipt.getItems().size() / 2) * 5;
    }
}
