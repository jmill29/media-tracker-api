package com.jmill29.tvtrackerapi.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthUtil {

    public static String extractUsernameFromAuthHeader(String authHeader) throws  IllegalArgumentException {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            throw new IllegalArgumentException("Missing or Invalid Authorization header");
        }

        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        
        // credentials = "username:password"
        return credentials.split(":", 2)[0];
    }

}
