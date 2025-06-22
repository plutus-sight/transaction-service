package com.plutussight.transactionservice.mapper;

import com.plutussight.transactionservice.controller.request.CreateTransactionGroupRequest;
import com.plutussight.transactionservice.controller.request.UpdateTransactionGroupRequest;
import com.plutussight.transactionservice.controller.response.PagedTransactionGroupResponse;
import com.plutussight.transactionservice.controller.response.TransactionGroupResponse;
import com.plutussight.transactionservice.entity.TransactionGroup;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = PaginationMapper.class)
public interface TransactionGroupMapper {
    TransactionGroupResponse toResponse(TransactionGroup transactionGroup);

    TransactionGroup toEntity(CreateTransactionGroupRequest request);

    @Mapping(target = "transactionGroups", source = "content")
    @Mapping(target = "pagination", source = ".")
    PagedTransactionGroupResponse toPagedResponse(Page<TransactionGroup> transactionGroupPage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UpdateTransactionGroupRequest request, @MappingTarget TransactionGroup entity);
}