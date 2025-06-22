package com.plutussight.transactionservice.mapper;

import com.plutussight.transactionservice.controller.request.TransactionRequest;
import com.plutussight.transactionservice.controller.response.PagedTransactionResponse;
import com.plutussight.transactionservice.controller.response.TransactionResponse;
import com.plutussight.transactionservice.entity.Transaction;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = PaginationMapper.class)
public interface TransactionMapper {
    TransactionResponse toResponse(Transaction transaction);

    Transaction toEntity(TransactionRequest request);

    @Mapping(target = "transactions", source = "content")
    @Mapping(target = "pagination", source = ".")
    PagedTransactionResponse toPagedResponse(Page<Transaction> page);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(TransactionRequest request, @MappingTarget Transaction entity);
}