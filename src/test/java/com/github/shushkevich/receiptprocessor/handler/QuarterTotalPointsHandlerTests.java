package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuarterTotalPointsHandlerTests {

    @ParameterizedTest
    @CsvSource({
            "35.35,     0",
            "9.00,      25",
            "9.25,      25",
            "9.50,      25",
            "9.75,      25",
            "9.80,      0"
    })
    public void testCalculatePoints(BigDecimal total, int points) {
        var receipt = new Receipt();
        receipt.setTotal(total);
        assertEquals(points, new QuarterTotalPointsHandler().calculatePoints(receipt));
    }
}
