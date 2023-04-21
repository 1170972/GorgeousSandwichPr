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

    public CommentController(CommentRepositoryWrapperJPA commentRepository){
        this.commentRepository = commentRepository;
    }

    @GetMapping("/comments/sandwich/{id}")
    public List<CommentDTO> listBySandwich(@PathVariable(value = "id") Long sandwichId){
        return CommentConverter.convertCommentListToDTO(commentRepository.findBySandwichId(sandwichId));
    }

    @GetMapping("/comments/email/{id}")
    public List<CommentDTO> listByEmail(@PathVariable(value = "id") String email){
        return CommentConverter.convertCommentListToDTO(commentRepository.findByEmail(email));
    }

    @PostMapping("/comments")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO){
        Comment comment = CommentConverter.convertFromDTO(commentDTO);
        if(comment == null){
            throw new IllegalArgumentException("One or more of the input values are wrong");
        }
        return CommentConverter.convertToDTO(commentRepository.save(comment));
    }
}
