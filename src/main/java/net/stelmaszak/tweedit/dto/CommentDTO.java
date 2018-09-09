package net.stelmaszak.tweedit.dto;

import lombok.*;
import net.stelmaszak.tweedit.entity.Comment;
import net.stelmaszak.tweedit.entity.Vote;

import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CommentDTO {

    private Comment comment;
    private Vote vote;

    public CommentDTO addUserVote(List<Vote> userVotes) {
        userVotes.stream()
                .filter(vote -> vote.isVoteForComment(this))
                .findFirst().ifPresent(this::setVote);
        return this;
    }

}
