package com.github.shushkevich.receiptprocessor;

import com.github.shushkevich.receiptprocessor.dto.PointsResp;
import com.github.shushkevich.receiptprocessor.dto.ReceiptReq;
import com.github.shushkevich.receiptprocessor.dto.ReceiptResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/receipts")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Receipt Processor API")
public class ReceiptController {

    private final ModelMapper modelMapper;
    private final ReceiptService receiptService;

    @Operation(summary = "Process Receipts")
    @PostMapping("/process")
    public ReceiptResp process(@RequestBody @Valid ReceiptReq body) {
        var receipt = modelMapper.map(body, Receipt.class);
        var result = receiptService.save(receipt);

        return modelMapper.map(result, ReceiptResp.class);
    }

    @Operation(summary = "Get Points")
    @GetMapping("/{id}/points")
    public PointsResp getPoints(@PathVariable Long id) {
        var receipt = receiptService.get(id).orElseThrow(() -> {
            log.warn("Access by invalid ID ({}) detected", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        return new PointsResp(receiptService.calculatePoints(receipt));
    }
}
