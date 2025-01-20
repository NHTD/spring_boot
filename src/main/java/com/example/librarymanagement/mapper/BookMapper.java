package com.example.librarymanagement.mapper;

import com.example.librarymanagement.dtos.request.BookRequest;
import com.example.librarymanagement.dtos.request.BookUpdateRequest;
import com.example.librarymanagement.dtos.response.BookResponse;
import com.example.librarymanagement.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "user", ignore = true)
    Book toBook(BookRequest request);

    BookResponse toBookResponse(Book book);

    void toUpdateBook(@MappingTarget Book book, BookUpdateRequest request);
}
