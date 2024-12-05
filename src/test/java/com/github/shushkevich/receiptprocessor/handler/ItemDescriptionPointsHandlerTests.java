package com.github.shushkevich.receiptprocessor.handler;

import com.github.shushkevich.receiptprocessor.Receipt;
import com.github.shushkevich.receiptprocessor.ReceiptItem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemDescriptionPointsHandlerTests {

    @ParameterizedTest
    @CsvSource({
            "Mountain Dew 12PK,                 6.49,   0",
            "Emils Cheese Pizza,                12.25,  3",
            "'   Klarbrunn 12-PK 12 FL OZ  ',   12.00,  3"
    })
    public void testCalculatePoints(String itemDescription, BigDecimal itemPrice, int points) {
        var receipt = new Receipt();
        receipt.setItems(List.of(new ReceiptItem(123L, itemDescription, itemPrice)));
        assertEquals(points, new ItemDescriptionPointsHandler().calculatePoints(receipt));
    }
}
