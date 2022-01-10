package com.kongge.dzuoj.repository;

import com.kongge.dzuoj.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
