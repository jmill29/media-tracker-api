
/**
 * Utility class for authentication-related helper methods.
 * <p>
 * Provides static methods for extracting user credentials from HTTP Basic Authorization headers.
 * This class is not intended to be instantiated.
 */
package com.jmill29.tvtrackerapi.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * Utility methods for extracting authentication information from HTTP headers.
 */
public class AuthUtil {


    /**
     * Extracts the username from a Basic Authorization header value.
     * <p>
     * The header must be in the format {@code "Basic base64(username:password)"}.
     *
     * @param authHeader the value of the HTTP Authorization header
     * @return the username extracted from the header
     * @throws IllegalArgumentException if the header is missing, invalid, or not in the expected format
     */
    public static String extractUsernameFromAuthHeader(String authHeader) throws IllegalArgumentException {
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
