package com.learn.start.controller;

import com.learn.start.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/live")
public class LiveController {

    private final SignalingSocketHandler signalingSocketHandler;

    public LiveController(SignalingSocketHandler signalingSocketHandler) {
        this.signalingSocketHandler = signalingSocketHandler;
    }

    @GetMapping("/rooms")
    public List<String> getActiveRooms(@AuthenticationPrincipal CustomUserDetails user) {
        return signalingSocketHandler.getActiveRoomIds();
    }

}
