package com.plutussight.transactionservice.controller;

import com.plutussight.transactionservice.constant.ResponseCode;
import com.plutussight.transactionservice.controller.request.CreditCardRequest;
import com.plutussight.transactionservice.controller.response.CreditCardResponse;
import com.plutussight.transactionservice.controller.response.PagedCreditCardResponse;
import com.plutussight.transactionservice.controller.response.ServiceResponse;
import com.plutussight.transactionservice.service.CreditCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/credit-cards")
@AllArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @GetMapping
    public ResponseEntity<ServiceResponse<PagedCreditCardResponse>> getAllCreditCards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PagedCreditCardResponse pagedResponse = creditCardService.getAllCreditCards(page, size);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, pagedResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<CreditCardResponse>> getCreditCardById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                new ServiceResponse<>(ResponseCode.SUCCESS, creditCardService.getCreditCardById(id)));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<CreditCardResponse>> createCreditCard(@RequestBody CreditCardRequest request) {
        CreditCardResponse response = creditCardService.createCreditCard(request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceResponse<CreditCardResponse>> updateCreditCard(
            @PathVariable UUID id, @RequestBody CreditCardRequest request) {
        CreditCardResponse response = creditCardService.updateCreditCard(id, request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable UUID id) {
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.noContent().build();
    }
}