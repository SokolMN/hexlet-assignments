package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookDTO> getAll() {
        var posts = bookRepository.findAll();
        var result = posts.stream()
                .map(bookMapper::map)
                .toList();
        return result;
    }

    public BookDTO create(BookCreateDTO bookData) {
        var book = bookMapper.map(bookData);
       // post.setAuthor(userUtils.getCurrentUser());
        bookRepository.save(book);
        var bookDTO = bookMapper.map(book);
        return bookDTO;
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));

        var bookDTO = bookMapper.map(book);
    //    bookDTO.setAuthorFirstName(book.getAuthor().getFirstName());
    //    bookDTO.setAuthorLastName(book.getAuthor().getLastName());
        return bookDTO;
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        var bookDTO = bookMapper.map(book);
        return bookDTO;
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
