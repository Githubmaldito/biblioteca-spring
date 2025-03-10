package com.ifpi.biblioteca.services;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService){
        this.emailService = emailService
    }

    @PostMapping
    public void sendEmail(@RequestBody Email email){
        emailService.sendEmail(email);
    }
}
