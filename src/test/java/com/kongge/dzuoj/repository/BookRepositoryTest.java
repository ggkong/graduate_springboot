package com.kongge.dzuoj.repository;

import com.kongge.dzuoj.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.plaf.IconUIResource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAll(){
        List<Book> books = bookRepository.findAll();
        System.out.println(books);
    }

    @Test
    void saveTest(){
        Book book = new Book();
        book.setAuthor("孔格");
        book.setName("Spring boot");
        Book save = bookRepository.save(book);
        System.out.println(save);
    }

    @Test
    void findBook(){
        Optional<Book> book = bookRepository.findById(1);
        System.out.println(book);
    }

    @Test
    void deleteBookById(){
        bookRepository.deleteById(161);
    }

    @Test
    void Test(){
        System.out.println(System.getProperty("user.dir")+"/main/resources/files/");
    }

}