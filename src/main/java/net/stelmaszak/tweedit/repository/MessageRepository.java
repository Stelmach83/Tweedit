package net.stelmaszak.tweedit.repository;

import net.stelmaszak.tweedit.entity.Message;
import net.stelmaszak.tweedit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> getMessagesByToUser(User user);

}
