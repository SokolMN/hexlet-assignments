package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var posts = authorRepository.findAll();
        var result = posts.stream()
                .map(authorMapper::map)
                .toList();
        return result;
    }

    public AuthorDTO create(AuthorCreateDTO authData) {
        var author = authorMapper.map(authData);
        authorRepository.save(author);
        var postDTO = authorMapper.map(author);
        return postDTO;
    }

    public AuthorDTO findById(Long id) {
        var post = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var postDTO = authorMapper.map(post);
        return postDTO;
    }

    public AuthorDTO update(AuthorUpdateDTO authtData, Long id) {
        var post = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        authorMapper.update(authtData, post);
        authorRepository.save(post);
        var postDTO = authorMapper.map(post);
        return postDTO;
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}
