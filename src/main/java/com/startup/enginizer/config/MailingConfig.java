package com.startup.enginizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Base64Utils;

import java.util.Properties;

/**
 * Created by dragos.triteanu on 11/15/15.
 */
@Configuration
@PropertySource("classpath:conf/mail.properties")
public class MailingConfig {

    @Autowired
    private Environment environment;

    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("platform.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("platform.mail.port")));
        mailSender.setUsername(environment.getProperty("platform.mail.username"));

        byte[] decode = Base64Utils.decode(environment.getProperty("platform.mail.password").getBytes());
        String password = new String(decode);

        mailSender.setPassword(password);

        Properties props  = new Properties();
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable",true);

        mailSender.setJavaMailProperties(props);
        return mailSender;
    }

}
