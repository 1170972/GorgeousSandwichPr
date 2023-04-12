package pt.isep.arqsoft.GorgeousSandwich.controller;

import org.springframework.web.bind.annotation.*;
import pt.isep.arqsoft.GorgeousSandwich.domain.comment.Comment;
import pt.isep.arqsoft.GorgeousSandwich.dto.comment.CommentConverter;
import pt.isep.arqsoft.GorgeousSandwich.dto.comment.CommentDTO;
import pt.isep.arqsoft.GorgeousSandwich.repository.comment.wrapper.CommentRepositoryWrapperJPA;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/gorgeous-sandwich")
public class CommentController {
    private CommentRepositoryWrapperJPA commentRepository;
    private CommentConverter commentConverter;

    public CommentController(CommentRepositoryWrapperJPA commentRepository,CommentConverter commentConverter){
        this.commentRepository = commentRepository;
        this.commentConverter = commentConverter;
    }

    @GetMapping("/comments/sandwich/{id}")
    public List<CommentDTO> listBySandwich(@PathVariable(value = "id") Long sandwichId){
        List<Comment> comments = commentRepository.findBySandwichId(sandwichId);
        return commentConverter.convertCommentListToDTO(comments);
    }

    @GetMapping("/comments/email/{id}")
    public List<CommentDTO> listByEmail(@PathVariable(value = "id") String email){
        List<Comment> comments = commentRepository.findByEmail(email);
        return commentConverter.convertCommentListToDTO(comments);
    }

    @PostMapping("/comments")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO){
        Comment comment = commentConverter.convertFromDTO(commentDTO);
        if(comment == null){
            throw new IllegalArgumentException("One or more of the input values are wrong");
        }
        comment = commentRepository.save(comment);
        return commentConverter.convertToDTO(comment);
    }
}
