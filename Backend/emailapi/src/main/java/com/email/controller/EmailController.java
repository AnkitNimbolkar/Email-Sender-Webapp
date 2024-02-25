package com.email.controller;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {
    @Autowired
    private EmailService emailService;
    @RequestMapping("/welcome")
    public String welcome()
    {
        return "hello, This is my email API";
    }

    @RequestMapping(value="/sendemail", method= RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request ) {

        System.out.println(request);
        boolean status = this.emailService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
        if (status) {
            return ResponseEntity.ok(new EmailResponse("Email is sent successfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email Not Sent Sent"));
        }
    }
}
