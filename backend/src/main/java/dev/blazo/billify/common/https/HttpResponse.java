package dev.blazo.billify.common.https;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

/**
 * Represents a standard HTTP response structure to be used across the application.
 * This class provides details about the HTTP response, including status, message,
 * and additional data.
 *
 * Attributes:
 * - `timeStamp`: The timestamp at which the response was generated.
 * - `status`: The HTTP status of the response (e.g., OK, BAD_REQUEST).
 * - `statusCode`: The numerical HTTP status code.
 * - `message`: A user-friendly message describing the response.
 * - `reason`: A brief reason or context for the response.
 * - `developerMessage`: A message intended for developers, providing additional details about the response.
 * - `data`: A map containing additional data relevant to the response, often used to hold entities or DTOs.
 *
 * This class uses Lombok annotations for automatic generation of boilerplate code such as getters,
 * setters, and builders, and it includes only non-default JSON properties in responses.
 *
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/30/2025
 */
@Data
@SuperBuilder
@JsonInclude(NON_DEFAULT)
public class HttpResponse {
    protected String timeStamp;
    protected HttpStatus status;
    protected int statusCode;
    protected String message;
    protected String reason;
    protected String developerMessage;
    protected Map<?, ?> data;
}
