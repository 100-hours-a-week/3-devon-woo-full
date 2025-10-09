package com.example.spring_practice.service;

import com.example.spring_practice.dto.BookDto;
import com.example.spring_practice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookDto> getAllBooks() {
        List<BookDto> books = bookRepository.findAll();
        books.sort(Comparator.comparing(BookDto::getId));
        return books;
    }

    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "도서를 찾을 수 없습니다. ID: " + id));
    }
}
