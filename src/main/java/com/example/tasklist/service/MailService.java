package com.example.tasklist.service;

import com.example.tasklist.domain.MailType;
import com.example.tasklist.domain.user.User;

import java.util.Properties;

public interface MailService {

    void sendEmail(User user, MailType type, Properties params);

}
