package com.plutussight.transactionservice.mapper;

import com.plutussight.transactionservice.controller.response.PaginationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PaginationMapper {
    @Mapping(target = "currentPage", expression = "java(page.getNumber() + 1)")
    @Mapping(target = "perPage", source = "size")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "totalItems", source = "totalElements")
    PaginationResponse toPaginationResponse(Page<?> page);
}