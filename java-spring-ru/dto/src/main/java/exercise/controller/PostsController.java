package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> showAllPosts(){
        var postList = postRepository.findAll();
        ArrayList<PostDTO> postDTOList = new ArrayList<>();

        for(Post post:postList){
            PostDTO postDTO = new PostDTO();
            postDTO.setBody(post.getBody());
            postDTO.setTitle(post.getTitle());
            postDTO.setId(post.getId());


            var commentList = commentRepository.findByPostId(post.getId());
            ArrayList<CommentDTO> commentDTOList = new ArrayList<>();

            for(Comment comment:commentList){
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setBody(comment.getBody());
                commentDTO.setId(comment.getId());
                commentDTOList.add(commentDTO);
            }
            postDTO.setComments(commentDTOList);
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }

    @GetMapping(path = "/{id}")
    public PostDTO showPostById(@PathVariable long id){
        var postDTO = new PostDTO();

        List<Comment> listComment = new ArrayList<>();
        ArrayList<CommentDTO> commentDTOList = new ArrayList<>();
        var post =  postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        postDTO.setId(post.getId());
        postDTO.setBody(post.getBody());
        postDTO.setTitle(post.getTitle());
        listComment = commentRepository.findByPostId(post.getId());
        for(Comment com : listComment){
            var commentDTO = new CommentDTO();
            commentDTO.setId(com.getId());
            commentDTO.setBody((com.getBody()));
            commentDTOList.add(commentDTO);
        }
        postDTO.setComments(commentDTOList);
        return postDTO;
    }

}
// END
