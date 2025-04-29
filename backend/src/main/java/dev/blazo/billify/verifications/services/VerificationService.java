package dev.blazo.billify.verifications.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/29/2025
 */
@Service
public class VerificationService {
    // TODO: Replace this URL for the frontend verification URL
    public String createVerificationUrl(String uniqueKey, String verificationType) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + verificationType + "/" + uniqueKey).toUriString();
    }
}
