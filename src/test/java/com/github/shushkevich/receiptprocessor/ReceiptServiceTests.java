package com.github.shushkevich.receiptprocessor;

import com.github.shushkevich.receiptprocessor.handler.PointsCalcHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTests {

    @Mock
    private ReceiptRepository repository;

    @Mock
    private PointsCalcHandler pointsCalcHandler1;

    @Mock
    private PointsCalcHandler pointsCalcHandler2;

    @InjectMocks
    private ReceiptService receiptService;

    @Test
    public void testSave() {
        var receipt = new Receipt();
        receipt.setRetailer("Target");
        when(repository.save(receipt)).thenReturn(receipt);

        var result = receiptService.save(receipt);

        assertNotNull(result);
        assertEquals(receipt, result);
        verify(repository).save(receipt);
    }

    @Test
    public void testGetReceiptFound() {
        var id = 100L;
        var receipt = new Receipt();
        receipt.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(receipt));

        var result = receiptService.get(id);

        assertTrue(result.isPresent());
        assertEquals(receipt, result.get());
        verify(repository).findById(id);
    }

    @Test
    public void testGetReceiptNotFound() {
        var id = 100L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        var result = receiptService.get(id);

        assertTrue(result.isEmpty());
        verify(repository).findById(id);
    }

    @Test
    public void testCalculatePoints() {
        var receipt = new Receipt();
        var points1 = 10;
        var points2 = 20;
        when(pointsCalcHandler1.calculatePoints(receipt)).thenReturn(points1);
        when(pointsCalcHandler2.calculatePoints(receipt)).thenReturn(points2);
        receiptService = new ReceiptService(List.of(pointsCalcHandler1, pointsCalcHandler2), repository);

        var result = receiptService.calculatePoints(receipt);

        assertEquals(points1 + points2, result);
        verify(pointsCalcHandler1).calculatePoints(receipt);
        verify(pointsCalcHandler2).calculatePoints(receipt);
    }
}
