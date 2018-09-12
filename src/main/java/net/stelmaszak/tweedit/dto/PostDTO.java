package net.stelmaszak.tweedit.dto;

import lombok.*;
import net.stelmaszak.tweedit.entity.Comment;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.entity.Vote;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PostDTO {

    private Post post;
    private Vote vote;
    private List<CommentDTO> comments;

    public PostDTO addVote(List<Vote> userVotes) {
        userVotes.stream()
                .filter(vote -> vote.isVoteForPost(this))
                .findFirst().ifPresent(this::setVote);
        return this;
    }

    public PostDTO addComments() {
        List<Comment> postCommments = this.post.getComments();
        List<CommentDTO> buildComments = new ArrayList<>();
        if (postCommments != null) {
            for (Comment c : postCommments) {
                buildComments.add(Comment.mapToCommentDTO(c));
            }
        }
        comments = buildComments;
        return this;
    }

    public Post getPostFromDto() {
        return this.post;
    }

}
