package net.stelmaszak.tweedit.dto;

import lombok.*;
import net.stelmaszak.tweedit.entity.Post;
import net.stelmaszak.tweedit.entity.Vote;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PostDTO {

    private Post post;
    private Vote vote;

}
