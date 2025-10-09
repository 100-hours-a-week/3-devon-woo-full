package com.example.spring_practice.repository;

import com.example.spring_practice.dto.BookDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {
    private final Map<Long, BookDto> storage = new LinkedHashMap<>();
    private long sequence = 0L;

    public BookRepository() {
        initializeData();
    }

    private void initializeData() {
        if (storage.isEmpty()) {
            save(new BookDto(null, "Clean Code", "Robert C. Martin",
                "소프트웨어 장인 정신을 담은 책입니다.", "9780132350884"));
            save(new BookDto(null, "객체지향의 사실과 오해", "조영호",
                "객체지향의 본질을 쉽게 설명합니다.", "9791186710770"));
            save(new BookDto(null, "Effective Java", "Joshua Bloch",
                "자바 개발자를 위한 베스트 프랙티스 모음집입니다.", "9780134685991"));
        }
    }

    public BookDto save(BookDto book) {
        if (book.getId() == null) {
            book.setId(++sequence);
        }
        storage.put(book.getId(), book);
        return book;
    }

    public Optional<BookDto> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<BookDto> findAll() {
        return new ArrayList<>(storage.values());
    }
}
