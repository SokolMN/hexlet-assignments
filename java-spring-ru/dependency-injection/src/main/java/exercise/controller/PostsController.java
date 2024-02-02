package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Post> getPosts(){
        return postRepository.findAll();
    }


    @GetMapping(path = "/{id}")
    public Post getOnePost(@PathVariable long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }


    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping(path = "/{id}")
    public Post updatePost(@RequestBody Post post, @PathVariable long id){
        var postForUpdate = postRepository.findById(id).get();
        postForUpdate.setBody(post.getBody());
        postForUpdate.setTitle(post.getTitle());
        postRepository.save(postForUpdate);
        return postForUpdate;
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable long id){
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }
}
// END
