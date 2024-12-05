package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.springframework.stereotype.Component;

/**
 * Rule:
 * One point for every alphanumeric character in the retailer name.
 */
@Component
public class RetailerPointsHandler implements PointsCalcHandler {

    @Override
    public int calculatePoints(Receipt receipt) {
        return receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
    }
}
