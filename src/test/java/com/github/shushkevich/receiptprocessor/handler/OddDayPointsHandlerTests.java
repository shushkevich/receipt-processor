package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OddDayPointsHandlerTests {

    @ParameterizedTest
    @CsvSource({
            "2022-01-01,     6",
            "2022-01-02,     0",
            "2022-01-03,     6",
            "2022-01-04,     0"
    })
    public void testCalculatePoints(LocalDate purchaseDate, int points) {
        var receipt = new Receipt();
        receipt.setPurchaseDate(purchaseDate);
        assertEquals(points, new OddDayPointsHandler().calculatePoints(receipt));
    }
}
