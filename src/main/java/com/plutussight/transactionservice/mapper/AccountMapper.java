package com.plutussight.transactionservice.mapper;

import com.plutussight.transactionservice.controller.request.CreateAccountRequest;
import com.plutussight.transactionservice.controller.request.UpdateAccountRequest;
import com.plutussight.transactionservice.controller.response.AccountResponse;
import com.plutussight.transactionservice.controller.response.PagedAccountResponse;
import com.plutussight.transactionservice.entity.Account;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = PaginationMapper.class)
public interface AccountMapper {
    AccountResponse toResponse(Account account);

    Account toEntity(CreateAccountRequest request);

    @Mapping(target = "accounts", source = "content")
    @Mapping(target = "pagination", source = ".")
    PagedAccountResponse toPagedResponse(Page<Account> page);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UpdateAccountRequest request, @MappingTarget Account entity);
}