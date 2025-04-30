package dev.blazo.billify.verifications.account_verifications.services;

import dev.blazo.billify.common.exceptions.ApiException;
import dev.blazo.billify.users.entities.User;
import dev.blazo.billify.verifications.account_verifications.entities.AccountVerification;
import dev.blazo.billify.verifications.account_verifications.repositories.AccountVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/28/2025
 */
@Service
@RequiredArgsConstructor
public class AccountVerificationService {

    private final AccountVerificationRepository accountVerificationRepository;

    public void save(AccountVerification accountVerification) {
        this.accountVerificationRepository.save(accountVerification);
    }

    /**
     * Creates and saves an AccountVerification entity for the given user and verification URL.
     *
     * @param user            the user to associate with the verification
     * @param verificationUrl the URL to be saved in the verification entity
     * @throws ApiException if saving, the account verification fails
     */
    public void createAndSaveAccountVerification(User user, String verificationUrl) {
        try {
            AccountVerification accountVerification = AccountVerification.builder().user(user).url(verificationUrl).build();

            save(accountVerification);
        } catch (Exception e) {
            throw new ApiException("Failed to create account verification: " + e.getMessage());
        }
    }
}
