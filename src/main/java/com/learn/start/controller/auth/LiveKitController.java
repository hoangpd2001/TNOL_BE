package com.learn.start.controller.auth;

import com.learn.start.controller.auth.LiveKitTokenGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiveKitController {

    private LiveKitTokenGenerator tokenGenerator = new LiveKitTokenGenerator();

    @GetMapping("/auth/api/livekit/token")
    public String getToken(@RequestParam String identity, @RequestParam String room) {
        return tokenGenerator.generateToken(identity, room);
    }
}
