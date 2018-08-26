package net.stelmaszak.tweedit.service;

import net.stelmaszak.tweedit.entity.Message;
import net.stelmaszak.tweedit.entity.User;

import java.util.List;

public interface MessageService {

    List<Message> getMessagesByToUser(User user);

    int getUnreadMessagesByUser(User user);

}
