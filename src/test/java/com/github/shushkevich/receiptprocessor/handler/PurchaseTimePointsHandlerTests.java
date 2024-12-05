package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseTimePointsHandlerTests {

    @ParameterizedTest
    @CsvSource({
            "11:01,     0",
            "13:59,     0",
            "14:00,     10",
            "14:01,     10",
            "15:59,     10",
            "16:00,     0"
    })
    public void testCalculatePoints(LocalTime purchaseTime, int points) {
        var receipt = new Receipt();
        receipt.setPurchaseTime(purchaseTime);
        assertEquals(points, new PurchaseTimePointsHandler().calculatePoints(receipt));
    }
}
