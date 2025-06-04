package com.plutussight.transactionservice.mapper;

import com.plutussight.transactionservice.controller.request.CreateCreditCardRequest;
import com.plutussight.transactionservice.controller.request.UpdateCreditCardRequest;
import com.plutussight.transactionservice.controller.response.CreditCardResponse;
import com.plutussight.transactionservice.controller.response.PagedCreditCardResponse;
import com.plutussight.transactionservice.entity.CreditCard;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = PaginationMapper.class)
public interface CreditCardMapper {
    CreditCardResponse toResponse(CreditCard creditCard);

    CreditCard toEntity(CreateCreditCardRequest request);

    @Mapping(target = "creditCards", source = "content")
    @Mapping(target = "pagination", source = ".")
    PagedCreditCardResponse toPagedResponse(Page<CreditCard> page);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UpdateCreditCardRequest request, @MappingTarget CreditCard entity);
}