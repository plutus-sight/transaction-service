package com.plutussight.transactionservice.controller;

import com.plutussight.transactionservice.entity.CreditCard;
import com.plutussight.transactionservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping
    public List<CreditCard> getAllCreditCards() {
        return creditCardService.getAllCreditCards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable UUID id) {
        return creditCardService.getCreditCardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CreditCard createCreditCard(@RequestBody CreditCard creditCard) {
        return creditCardService.saveCreditCard(creditCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable UUID id, @RequestBody CreditCard creditCard) {
        if (!id.equals(creditCard.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(creditCardService.saveCreditCard(creditCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable UUID id) {
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.noContent().build();
    }
}