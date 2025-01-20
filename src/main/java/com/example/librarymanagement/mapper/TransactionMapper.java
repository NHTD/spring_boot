package com.example.librarymanagement.mapper;

import com.example.librarymanagement.dtos.request.TransactionRequest;
import com.example.librarymanagement.dtos.response.TransactionResponse;
import com.example.librarymanagement.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toTransaction(TransactionRequest request);
    TransactionResponse toTransactionResponse(Transaction category);

    void toUpdateTransaction(@MappingTarget Transaction transaction, TransactionRequest request);
}
