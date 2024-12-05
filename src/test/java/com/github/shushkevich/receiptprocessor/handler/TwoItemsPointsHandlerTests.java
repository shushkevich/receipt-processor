package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import com.github.shushkevich.receiptprocessor.ReceiptItem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoItemsPointsHandlerTests {

    @ParameterizedTest
    @CsvSource({
            "1,     0",
            "2,     5",
            "3,     5",
            "4,     10",
            "5,     10"
    })
    public void testCalculatePoints(int itemsCount, int points) {
        var receipt = new Receipt();
        receipt.setItems(IntStream.range(0, itemsCount)
                .mapToObj(i -> new ReceiptItem((long) i, "descr", new BigDecimal("1.00")))
                .toList());
        assertEquals(points, new TwoItemsPointsHandler().calculatePoints(receipt));
    }
}
