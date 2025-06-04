package com.plutussight.transactionservice.controller;

import com.plutussight.transactionservice.controller.request.CreateCreditCardRequest;
import com.plutussight.transactionservice.controller.request.UpdateCreditCardRequest;
import com.plutussight.transactionservice.controller.response.CreditCardResponse;
import com.plutussight.transactionservice.controller.response.PagedCreditCardResponse;
import com.plutussight.transactionservice.controller.response.ServiceResponse;
import com.plutussight.transactionservice.service.CreditCardService;
import com.plutussight.transactionservice.util.ResponseCode;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit-cards")
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
    public ResponseEntity<ServiceResponse<CreditCardResponse>> getCreditCardById(@PathVariable String id) {
        return ResponseEntity.ok(
                new ServiceResponse<>(ResponseCode.SUCCESS, creditCardService.getCreditCardById(id)));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<CreditCardResponse>> createCreditCard(@RequestBody CreateCreditCardRequest request) {
        CreditCardResponse response = creditCardService.createCreditCard(request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceResponse<CreditCardResponse>> updateCreditCard(
            @PathVariable String id, @RequestBody UpdateCreditCardRequest request) {
        CreditCardResponse response = creditCardService.updateCreditCard(id, request);
        return ResponseEntity.ok(new ServiceResponse<>(ResponseCode.SUCCESS, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable String id) {
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.noContent().build();
    }
}