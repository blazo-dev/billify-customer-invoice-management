package dev.blazo.billify.users.services;

import dev.blazo.billify.users.dtos.UserDto;
import dev.blazo.billify.users.entities.User;
import org.springframework.beans.BeanUtils;

/**
 * Utility class that provides methods for mapping between the User entity
 * and the UserDto data transfer object.
 * This class is designed to simplify the conversion between these two representations.
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/30/2025
 */
public class UserMapper {
    /**
     * Converts a User entity into a UserDto object by copying properties.
     *
     * @param user the User entity to be converted to a UserDto
     * @return a UserDto object containing the data from the given User entity
     */
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    /**
     * Converts a UserDto object into a User entity by copying properties.
     *
     * @param userDto the UserDto object to be converted to a User entity
     * @return a User entity containing the data from the given UserDto object
     */
    public static User toEntity(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
