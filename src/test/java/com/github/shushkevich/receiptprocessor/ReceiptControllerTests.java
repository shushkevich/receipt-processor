package com.github.shushkevich.receiptprocessor;

import com.github.shushkevich.receiptprocessor.dto.ReceiptReq;
import com.github.shushkevich.receiptprocessor.dto.ReceiptResp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ReceiptControllerTests {

    private MockMvc mockMvc;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ReceiptService receiptService;

    @InjectMocks
    private ReceiptController receiptController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(receiptController).build();
    }

    @Test
    public void testProcessSuccess() throws Exception {
        var reqBody = new ReceiptReq();
        var receipt = new Receipt();
        var receiptResp = new ReceiptResp();
        receiptResp.setId(100);

        when(modelMapper.map(any(ReceiptReq.class), eq(Receipt.class))).thenReturn(receipt);
        when(receiptService.save(receipt)).thenReturn(receipt);
        when(modelMapper.map(receipt, ReceiptResp.class)).thenReturn(receiptResp);

        mockMvc.perform(post("/receipts/process")
                        .contentType("application/json")
                        .content("""
                                {
                                    "retailer": "Walgreens",
                                    "purchaseDate": "2022-01-02",
                                    "purchaseTime": "08:13",
                                    "total": "2.65",
                                    "items": [
                                        {"shortDescription": "Pepsi - 12-oz", "price": "1.25"},
                                        {"shortDescription": "Dasani", "price": "1.40"}
                                    ]
                                }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(receiptResp.getId()));

        verify(modelMapper).map(any(ReceiptReq.class), eq(Receipt.class));
        verify(receiptService).save(receipt);
        verify(modelMapper).map(receipt, ReceiptResp.class);
    }

    @Test
    public void testProcessBadRequestIfValidationFailed() throws Exception {
        mockMvc.perform(post("/receipts/process")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPointsSuccess() throws Exception {
        var points = 12345;
        var receiptId = 100L;
        var receipt = new Receipt();
        receipt.setId(receiptId);
        when(receiptService.get(receiptId)).thenReturn(Optional.of(receipt));
        when(receiptService.calculatePoints(receipt)).thenReturn(points);

        mockMvc.perform(get("/receipts/{id}/points", receiptId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points").value(points));

        verify(receiptService).get(receiptId);
        verify(receiptService).calculatePoints(receipt);
    }

    @Test
    public void testGetPointsNotFoundIfWrongReceiptId() throws Exception {
        var receiptId = 100L;
        when(receiptService.get(receiptId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/receipts/{id}/points", receiptId))
                .andExpect(status().isNotFound());

        verify(receiptService).get(receiptId);
    }
}
