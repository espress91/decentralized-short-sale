package com.github.espress91.decentralizedShortSale.data.dto;

import com.github.espress91.decentralizedShortSale.data.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private Long id;

    private Long currentEtherPrice;

    private Long rentFee;

    private LocalDateTime regTime;

    private LocalDateTime delTime;

    public User toEntity() {
        User user = new User();

        user.setRegTime(LocalDateTime.now());
        user.setDelTime(LocalDateTime.now());

        return user;
    }

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());

        return userDto;
    }

}
