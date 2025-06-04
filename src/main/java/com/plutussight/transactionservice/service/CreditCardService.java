package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.controller.request.CreateCreditCardRequest;
import com.plutussight.transactionservice.controller.request.UpdateCreditCardRequest;
import com.plutussight.transactionservice.controller.response.CreditCardResponse;
import com.plutussight.transactionservice.controller.response.PagedCreditCardResponse;
import com.plutussight.transactionservice.entity.CreditCard;
import com.plutussight.transactionservice.exception.ResourceNotFoundException;
import com.plutussight.transactionservice.mapper.CreditCardMapper;
import com.plutussight.transactionservice.repository.jpa.CreditCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;

    public PagedCreditCardResponse getAllCreditCards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CreditCard> creditCardPage = creditCardRepository.findAll(pageable);
        return creditCardMapper.toPagedResponse(creditCardPage);
    }

    public CreditCardResponse getCreditCardById(String id) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found with id: " + id));
        return creditCardMapper.toResponse(creditCard);
    }

    public CreditCardResponse createCreditCard(CreateCreditCardRequest request) {
        CreditCard creditCard = creditCardMapper.toEntity(request);
        return creditCardMapper.toResponse(creditCardRepository.save(creditCard));
    }

    public CreditCardResponse updateCreditCard(String id, UpdateCreditCardRequest request) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found with id: " + id));
        creditCardMapper.updateEntityFromRequest(request, creditCard);
        return creditCardMapper.toResponse(creditCardRepository.save(creditCard));
    }

    public void deleteCreditCard(String id) {
        creditCardRepository.deleteById(id);
    }
}