package net.stelmaszak.tweedit.dto;

import lombok.*;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.entity.Vote;

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

    public PostDTO addVote(List<Vote> userVotes) {
        userVotes.stream()
                .filter(vote -> vote.isVoteForPost(this))
                .findFirst().ifPresent(this::setVote);
        return this;
    }

}
