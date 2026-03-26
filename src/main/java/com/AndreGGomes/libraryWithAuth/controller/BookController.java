package com.AndreGGomes.libraryWithAuth.controller;

import com.AndreGGomes.libraryWithAuth.model.Book;
import com.AndreGGomes.libraryWithAuth.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    public BookRepository bookRepository;

    @GetMapping("/get")
    public List<Book> getBook(){
        return this.bookRepository.findAll();
    }

    @PostMapping("/save")
    public Book saveBook(@RequestBody Book data){
        return this.bookRepository.save(data);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book data){
        return this.bookRepository.save(data);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id){
        this.bookRepository.deleteById(id);
    }

}
