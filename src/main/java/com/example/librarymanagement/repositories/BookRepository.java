package com.example.librarymanagement.repositories;

import com.example.librarymanagement.enums.BookStatusEnum;
import com.example.librarymanagement.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b")
    Page<Book> getAllBook(Pageable pageable);

    @Query("select b from Book b where (:bookStatus is null or :bookStatus = '' or b.status = %:bookStatus%)")
    Page<Book> getAllByStatus(Pageable pageable, BookStatusEnum bookStatus);
}
