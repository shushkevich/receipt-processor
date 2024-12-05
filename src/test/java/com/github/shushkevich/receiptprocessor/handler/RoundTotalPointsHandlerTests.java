package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundTotalPointsHandlerTests {

    @ParameterizedTest
    @CsvSource({
            "35.35,     0",
            "9.00,      50"
    })
    public void testCalculatePoints(BigDecimal total, int points) {
        var receipt = new Receipt();
        receipt.setTotal(total);
        assertEquals(points, new RoundTotalPointsHandler().calculatePoints(receipt));
    }
}
