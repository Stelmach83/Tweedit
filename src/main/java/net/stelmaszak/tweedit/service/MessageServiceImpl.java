package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Message;
import net.stelmaszak.tweedit.entity.User;
import net.stelmaszak.tweedit.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public List<Message> getMessagesByToUser(User user) {
        return messageRepository.getMessagesByToUser(user);
    }

    public int getUnreadMessagesByUser(User user) {
        return (int) getMessagesByToUser(user).stream()
                .filter(m -> m.getMessageRead() == 0)
                .count();

    }

    @Override
    public Message getMessagyById(Long id) {
        return messageRepository.getOne(id);
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
