package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.controller.request.DebtTransactionRequest;
import com.plutussight.transactionservice.controller.response.DebtTransactionResponse;
import com.plutussight.transactionservice.controller.response.PagedDebtTransactionResponse;
import com.plutussight.transactionservice.entity.CreditCard;
import com.plutussight.transactionservice.entity.DebtTransaction;
import com.plutussight.transactionservice.exception.ResourceNotFoundException;
import com.plutussight.transactionservice.mapper.DebtTransactionMapper;
import com.plutussight.transactionservice.repository.jpa.CreditCardRepository;
import com.plutussight.transactionservice.repository.jpa.DebtTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DebtTransactionService {

    private final DebtTransactionRepository debtTransactionRepository;
    private final DebtTransactionMapper debtTransactionMapper;
    private final CreditCardRepository creditCardRepository;

    public PagedDebtTransactionResponse getAllDebtTransactions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DebtTransaction> debtTransactionPage = debtTransactionRepository.findAllByDeletedAtIsNull(pageable);
        return debtTransactionMapper.toPagedResponse(debtTransactionPage);
    }

    public DebtTransactionResponse getDebtTransactionById(UUID id) {
        DebtTransaction debtTransaction = debtTransactionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("DebtTransaction not found with id: " + id));
        return debtTransactionMapper.toResponse(debtTransaction);
    }

    public DebtTransactionResponse createDebtTransaction(DebtTransactionRequest request) {
        CreditCard creditCard = creditCardRepository.findById(request.getCreditCardId())
            .orElseThrow(() -> new ResourceNotFoundException("CreditCard not found with id: " + request.getCreditCardId()));
        
        DebtTransaction debtTransaction = debtTransactionMapper.toEntity(request);
        debtTransaction.setCreditCard(creditCard);

        return debtTransactionMapper.toResponse(debtTransactionRepository.save(debtTransaction));
    }

    public DebtTransactionResponse updateDebtTransaction(UUID id, DebtTransactionRequest request) {
        DebtTransaction existingDebtTransaction = debtTransactionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("DebtTransaction not found with id: " + id));
        
        debtTransactionMapper.updateEntityFromDto(request, existingDebtTransaction);
        DebtTransaction updatedDebtTransaction = debtTransactionRepository.save(existingDebtTransaction);
        return debtTransactionMapper.toResponse(updatedDebtTransaction);
    }

    public void deleteDebtTransaction(UUID id) {
        if (!debtTransactionRepository.existsById(id)) {
            throw new ResourceNotFoundException("DebtTransaction not found with id: " + id);
        }
        debtTransactionRepository.deleteById(id);
    }
}