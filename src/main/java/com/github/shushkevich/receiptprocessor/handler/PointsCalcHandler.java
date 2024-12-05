package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;

public interface PointsCalcHandler {

    int calculatePoints(Receipt receipt);
}
