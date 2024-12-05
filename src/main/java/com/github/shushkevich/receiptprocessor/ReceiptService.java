package com.github.shushkevich.receiptprocessor;

import com.github.shushkevich.receiptprocessor.handler.PointsCalcHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final List<PointsCalcHandler> pointsCalcHandlers;
    private final ReceiptRepository repository;

    @Transactional
    public Receipt save(Receipt receipt) {
        return repository.save(receipt);
    }

    @Transactional
    public Optional<Receipt> get(Long id) {
        return repository.findById(id);
    }

    @Cacheable(value = "pointsCache", key = "#receipt.id")
    public int calculatePoints(Receipt receipt) {
        return pointsCalcHandlers.stream()
                .mapToInt(handler -> handler.calculatePoints(receipt))
                .sum();
    }
}
