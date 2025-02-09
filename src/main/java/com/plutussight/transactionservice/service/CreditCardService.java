package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.entity.CreditCard;
import com.plutussight.transactionservice.repository.jpa.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    public Optional<CreditCard> getCreditCardById(UUID id) {
        return creditCardRepository.findById(id);
    }

    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public void deleteCreditCard(UUID id) {
        creditCardRepository.deleteById(id);
    }
}