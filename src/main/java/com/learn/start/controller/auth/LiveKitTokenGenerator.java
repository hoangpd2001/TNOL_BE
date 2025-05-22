package com.learn.start.controller.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LiveKitTokenGenerator {

    private final String apiKey = "YOUR_API_KEY";
    private final String apiSecret = "YOUR_API_SECRET";

    public String generateToken(String identity, String room) {

        Algorithm algorithm = Algorithm.HMAC256(apiSecret);

        Instant now = Instant.now();
        Instant exp = now.plusSeconds(3600); // token expire 1h

        // Phần grant video room
        Map<String, Object> videoGrant = new HashMap<>();
        videoGrant.put("room", room);

        Map<String, Object> grants = new HashMap<>();
        grants.put("video", videoGrant);

        String token = JWT.create()
                .withIssuer(apiKey)
                .withSubject(identity)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .withClaim("grants", grants)
                .sign(algorithm);

        return token;
    }
}
