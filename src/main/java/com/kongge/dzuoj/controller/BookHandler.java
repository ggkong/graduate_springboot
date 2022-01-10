package com.kongge.dzuoj.controller;

import com.kongge.dzuoj.entity.Book;
import com.kongge.dzuoj.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Book> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return bookRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody Book book){
        System.out.println(book);
        Book result = bookRepository.save(book);
        if(result != null){
            return "success";
        }else {
            return "error";
        }
    }

    @GetMapping("/findById/{id}")
    public Optional<Book> findBookById(@PathVariable("id") Integer id){
        return bookRepository.findById(id);
    }

    @PostMapping("/modifyBook")
    public String modify(@RequestBody Book book){
        System.out.println(book);
        // springData jpa 中没有modify 方法 直接用 save 方法就行
        Book book1 = bookRepository.save(book);
        if(book1 != null){
            return "success";
        }else {
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        bookRepository.deleteById(id);
    }

}
