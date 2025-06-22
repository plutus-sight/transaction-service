package com.plutussight.transactionservice.service;

import com.plutussight.transactionservice.controller.request.CreateTransactionGroupRequest;
import com.plutussight.transactionservice.controller.request.UpdateTransactionGroupRequest;
import com.plutussight.transactionservice.controller.response.PagedTransactionGroupResponse;
import com.plutussight.transactionservice.controller.response.TransactionGroupResponse;
import com.plutussight.transactionservice.entity.TransactionGroup;
import com.plutussight.transactionservice.exception.ResourceNotFoundException;
import com.plutussight.transactionservice.mapper.TransactionGroupMapper;
import com.plutussight.transactionservice.repository.jpa.TransactionGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionGroupService {

    private final TransactionGroupRepository transactionGroupRepository;
    private final TransactionGroupMapper transactionGroupMapper;

    public PagedTransactionGroupResponse getAllTransactionGroups(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TransactionGroup> transactionGroupPage = transactionGroupRepository.findAllByDeletedAtIsNull(pageable);
        return transactionGroupMapper.toPagedResponse(transactionGroupPage);
    }

    public TransactionGroupResponse getTransactionGroupByCode(String code) {
        TransactionGroup transactionGroup = transactionGroupRepository.findByCodeAndDeletedAtIsNull(code)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction Group not found"));
        return transactionGroupMapper.toResponse(transactionGroup);
    }

    public TransactionGroupResponse createTransactionGroup(CreateTransactionGroupRequest request) {
        TransactionGroup transactionGroup = transactionGroupMapper.toEntity(request);
        TransactionGroup savedTransactionGroup = transactionGroupRepository.save(transactionGroup);
        return transactionGroupMapper.toResponse(savedTransactionGroup);
    }

    public TransactionGroupResponse updateTransactionGroup(String code, UpdateTransactionGroupRequest request) {
        TransactionGroup transactionGroup = transactionGroupRepository.findByCodeAndDeletedAtIsNull(code)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction Group not found"));
        transactionGroupMapper.updateEntityFromRequest(request, transactionGroup);
        TransactionGroup updatedTransactionGroup = transactionGroupRepository.save(transactionGroup);
        return transactionGroupMapper.toResponse(updatedTransactionGroup);
    }
}