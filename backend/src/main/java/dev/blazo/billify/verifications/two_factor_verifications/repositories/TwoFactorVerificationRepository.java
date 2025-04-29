package dev.blazo.billify.verifications.two_factor_verifications.repositories;

import dev.blazo.billify.verifications.two_factor_verifications.entities.TwoFactorVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/27/2025
 */
@Repository
public interface TwoFactorVerificationRepository extends JpaRepository<TwoFactorVerification, Long> {

}
