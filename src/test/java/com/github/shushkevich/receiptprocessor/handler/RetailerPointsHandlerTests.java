package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetailerPointsHandlerTests {

    @ParameterizedTest
    @CsvSource({
            "Target,        6",
            "Walmart,       7",
            "Food 4 Less,   9",
    })
    public void testCalculatePoints(String retailer, int points) {
        var receipt = new Receipt();
        receipt.setRetailer(retailer);
        assertEquals(points, new RetailerPointsHandler().calculatePoints(receipt));
    }
}
