package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class EmailController {
    @GetMapping("/email")
    @ApiMessage("Send simple email")
    public String sendSimpleEmail() {
        return "ok";
    }
}
