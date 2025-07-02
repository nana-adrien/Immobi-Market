package empire.digiprem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
 class NotificationModuleConfig {
    @Bean
    public fun javaMailSender():JavaMailSender {
        val sender = JavaMailSenderImpl()
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("empire.digiprem@gmail.com");
        sender.setPassword("wzox tqit lefm iiza");

       val  props = sender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return sender;
    }
}
