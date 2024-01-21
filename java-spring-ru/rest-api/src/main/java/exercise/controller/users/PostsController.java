package exercise.controller.users;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
public class PostsController {
    private List<Post> posts = Data.getPosts();
    @GetMapping("/api/users/{id}/posts")
    public ResponseEntity<List<Post>> show(@PathVariable int id){
        var page = posts.stream()
                .filter(p -> p.getUserId() == id).toList();
                //.findFirst();

        if(!page.isEmpty())
            return ResponseEntity.ok().body(page);
        else
            return  ResponseEntity.notFound().build();
    }

    @PostMapping("/api/users/{id}/posts")
    public ResponseEntity<Post> create(@RequestBody Post post, @PathVariable int id){
        post.setUserId(id);
        posts.add(post);
        return ResponseEntity.status(201).body(post);
    }
}
// END
