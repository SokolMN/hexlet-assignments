package exercise.controller;

import java.util.List;

import exercise.dto.*;
import exercise.mapper.AuthorMapper;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("")
    public List<BookDTO> index() {
        return bookService.getAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@Valid @RequestBody BookCreateDTO bookData) {
        return bookService.create(bookData);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO update(@RequestBody BookUpdateDTO bookUpdateDTO, @PathVariable Long id) {
        return bookService.update(bookUpdateDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO show(@PathVariable Long id) {
       return bookService.findById(id);
    }
    // END
}
