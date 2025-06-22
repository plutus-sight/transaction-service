package com.plutussight.transactionservice.mapper;

import com.plutussight.transactionservice.controller.request.DebtTransactionRequest;
import com.plutussight.transactionservice.controller.response.DebtTransactionResponse;
import com.plutussight.transactionservice.controller.response.PagedDebtTransactionResponse;
import com.plutussight.transactionservice.entity.DebtTransaction;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = PaginationMapper.class)
public interface DebtTransactionMapper {
    DebtTransactionResponse toResponse(DebtTransaction debtTransaction);

    DebtTransaction toEntity(DebtTransactionRequest request);

    @Mapping(target = "debtTransactions", source = "content")
    @Mapping(target = "pagination", source = ".")
    PagedDebtTransactionResponse toPagedResponse(Page<DebtTransaction> page);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DebtTransactionRequest request, @MappingTarget DebtTransaction entity);
}