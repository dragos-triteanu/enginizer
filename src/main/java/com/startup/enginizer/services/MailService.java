package com.startup.enginizer.services;

import com.startup.enginizer.model.dto.MailMessage;
import com.startup.enginizer.model.entities.User;
import com.startup.enginizer.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * Created by dragos.triteanu on 11/15/15.
 */
@Service
public class MailService {
    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Async
    public void sendEmailToAllAdmins(final MailMessage mailMessage, final String fileLocation) throws Exception {
        LOG.info("Sending mail message from {} to {}", mailMessage.getSender(), mailMessage.getReceiver());
        List<User> allAdmins = userRepository.getAllAdmins();
        if(allAdmins.isEmpty()){
            throw new RuntimeException("There was an error sending your request");
        }

        String to = "";
        for(User user : allAdmins){
            to += user.getMail();
            to += ";";
        }
        to = to.substring(0,to.lastIndexOf(";"));

        mailMessage.withReceiver(to);

        LOG.info("Sending mail message from {} to {}",mailMessage.getSender(),mailMessage.getReceiver());
        final MimeMessage mimeMail = createMimeMail(mailMessage,fileLocation);
        mailSender.send(mimeMail);
    }

    private MimeMessage createMimeMail(final MailMessage mailMessage, final String fileLocation) throws Exception {
        MimeMessage email = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(email, true);
        helper.setFrom(new InternetAddress(mailMessage.getSender()));
        helper.setTo(new InternetAddress(mailMessage.getReceiver()));
        helper.setSubject(mailMessage.getSubject());
        helper.setText("Mesaj primit de la utilizatorul " + mailMessage.getSender() + ":\n" + mailMessage.getContent());

        if(fileLocation != null){
            helper.addAttachment("file",new File(fileLocation));
        }

        return  email;
    }
}