package mz.sixsense.mail.service.impl;

import mz.sixsense.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender emailSender;

    private String ePw;

    @Override
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);

        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("끼룩로드 회원가입 이메일 인증 번호 입니다.");

        String msgg = "";

        // msgg += "<img src=../resources/static/image/emailheader.jpg />"; // header image
        msgg += "<h1>안녕하세요</h1>";
        msgg += "<h1>끼룩로드 입니다</h1>";
        msgg += "<br>";
        msgg += "<p>아래 인증코드를 페이지에 입력해주세요</p>";
        msgg += "<br>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black'>";
        msgg += "<h3 style='color:blue'>회원가입 인증코드 입니다</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "<strong>" + ePw + "</strong></div><br/>"; // 메일에 인증번호 ePw 넣기
        msgg += "</div>";
        // msgg += "<img src=../resources/static/image/emailfooter.jpg />"; // footer image

        message.setText(msgg, "utf-8", "html");

        message.setFrom(new InternetAddress("kimjh9334@naver.com", "끼룩로드 관리자"));

//        System.out.println("********creatMessage 함수에서 생성된 msgg 메시지********" + msgg);
//
//        System.out.println("********creatMessage 함수에서 생성된 리턴 메시지********" + message);

        return message;
    }

    @Override
    public MimeMessage passwordFindCreateMessage(String to) throws MessagingException, UnsupportedEncodingException {
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);

        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("끼룩로드 변경된 비밀번호 입니다.");

        String msgg = "";

        // msgg += "<img src=../resources/static/image/emailheader.jpg />"; // header image
        msgg += "<h1>안녕하세요</h1>";
        msgg += "<h1>끼룩로드 입니다</h1>";
        msgg += "<br>";
        msgg += "<p>새로 변경된 비밀번호 입니다</p>";
        msgg += "<br>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black'>";
        msgg += "<h3 style='color:blue'>비밀번호 입니다</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "<strong>" + ePw + "</strong></div><br/>";
        msgg += "</div>";

        message.setText(msgg, "utf-8", "html");

        message.setFrom(new InternetAddress("kimjh9334@naver.com", "끼룩로드 관리자"));

        return message;
    }

    @Override
    public String createKey() {

        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }

        return key.toString();
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        ePw = createKey(); // 랜덤 인증번호 생성

        MimeMessage message = createMessage(to); // 메일 발송
        try {// 예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw; // 메일로 보냈던 인증 코드를 서버로 반환
    }

    @Override
    public String passwordFindMessage(String to) throws Exception {
        ePw = createKey();

        MimeMessage message = passwordFindCreateMessage(to);
        emailSender.send(message);

        return ePw;

    }
}


































