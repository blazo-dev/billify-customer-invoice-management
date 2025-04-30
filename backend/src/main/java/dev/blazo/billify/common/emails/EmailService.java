package dev.blazo.billify.common.emails;

import dev.blazo.billify.users.entities.User;
import dev.blazo.billify.verifications.enums.VerificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/29/2025
 */
@Service
@RequiredArgsConstructor
public class EmailService {

    public void sendVerificationUrlEmail(User user, String url, VerificationType vetificationType) {

    }
}
