package com.plutussight.transactionservice.mapper;

import com.plutussight.transactionservice.controller.request.CreateTransactionRequest;
import com.plutussight.transactionservice.controller.request.UpdateTransactionRequest;
import com.plutussight.transactionservice.controller.response.PagedTransactionResponse;
import com.plutussight.transactionservice.controller.response.TransactionResponse;
import com.plutussight.transactionservice.entity.Transaction;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = PaginationMapper.class)
public interface TransactionMapper {
    TransactionResponse toResponse(Transaction transaction);

    Transaction toEntity(CreateTransactionRequest request);

    @Mapping(target = "transactions", source = "content")
    @Mapping(target = "pagination", source = ".")
    PagedTransactionResponse toPagedResponse(Page<Transaction> page);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UpdateTransactionRequest request, @MappingTarget Transaction entity);
}