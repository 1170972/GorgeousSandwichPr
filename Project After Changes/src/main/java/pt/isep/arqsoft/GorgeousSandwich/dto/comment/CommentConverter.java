package pt.isep.arqsoft.GorgeousSandwich.dto.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.comment.Comment;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;

@Service
public class CommentConverter {
	
    public static CommentDTO convertToDTO(Comment comment){
        if(comment.obtainCommentId() == null){
            return new CommentDTO(comment.obtainDescription().obtainName(),
                    comment.obtainSandwichId().obtainID(),comment.obtainEmail().obtainName());
        }
        return new CommentDTO(comment.obtainCommentId().obtainID(), comment.obtainDescription().obtainName(),
                comment.obtainSandwichId().obtainID(),comment.obtainEmail().obtainName());
    }

    public static Comment convertFromDTO(CommentDTO commentDTO){
        return new Comment(Description.valueOf(commentDTO.description), SandwichID.valueOf(commentDTO.sandwichId), UserEmail.valueOf(commentDTO.email));
    }

    public static List<CommentDTO> convertCommentListToDTO(List<Comment> commentList){
        List<CommentDTO> commentsDTO = new ArrayList<>();
        for (Comment c : commentList){
        	commentsDTO.add(convertToDTO(c));
        }
        return commentsDTO;
    }

}
