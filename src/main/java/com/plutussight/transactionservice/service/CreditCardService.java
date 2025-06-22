package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.controller.request.CreditCardRequest;
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

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;

    public PagedCreditCardResponse getAllCreditCards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CreditCard> creditCardPage = creditCardRepository.findAllByDeletedAtIsNull(pageable);
        return creditCardMapper.toPagedResponse(creditCardPage);
    }

    public CreditCardResponse getCreditCardById(UUID id) {
        CreditCard creditCard = creditCardRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found with id: " + id));
        return creditCardMapper.toResponse(creditCard);
    }

    public CreditCardResponse createCreditCard(CreditCardRequest request) {
        CreditCard creditCard = creditCardMapper.toEntity(request);
        return creditCardMapper.toResponse(creditCardRepository.save(creditCard));
    }

    public CreditCardResponse updateCreditCard(UUID id, CreditCardRequest request) {
        CreditCard creditCard = creditCardRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit card not found with id: " + id));
        creditCardMapper.updateEntityFromRequest(request, creditCard);
        return creditCardMapper.toResponse(creditCardRepository.save(creditCard));
    }

    public void deleteCreditCard(UUID id) {
        creditCardRepository.deleteById(id);
    }
}