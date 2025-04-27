package dev.blazo.billify.events.enums;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/27/2025
 */
public enum EventType {
    LOGIN_ATTEMPT,
    LOGIN_ATTEMPT_FAILURE,
    LOGIN_ATTEMPT_SUCCESS,
    PROFILE_UPDATE,
    PROFILE_PICTURE_UPDATE,
    ROLE_UPDATE,
    ACCOUNT_SETTINGS_UPDATE,
    PASSWORD_UPDATE,
    MFA_UPDATE
}
