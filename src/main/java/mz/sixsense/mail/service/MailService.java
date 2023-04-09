package mz.sixsense.mail.service;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public interface MailService {

    MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;

    MimeMessage passwordFindCreateMessage(String to) throws MessagingException, UnsupportedEncodingException;

    String createKey();

    String sendSimpleMessage(String to) throws Exception;

    String passwordFindMessage(String to) throws Exception;

}
