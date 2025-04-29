package dev.blazo.billify.verifications.account_verifications.services;

import dev.blazo.billify.verifications.account_verifications.entities.AccountVerification;
import dev.blazo.billify.verifications.account_verifications.repositories.AccountVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/28/2025
 */
@Service
public class AccountVerificationService {

    private final AccountVerificationRepository accountVerificationRepository;

    @Autowired
    public AccountVerificationService(AccountVerificationRepository accountVerificationRepository) {
        this.accountVerificationRepository = accountVerificationRepository;
    }


    public void save(AccountVerification accountVerification) {
        this.accountVerificationRepository.save(accountVerification);
    }
}
