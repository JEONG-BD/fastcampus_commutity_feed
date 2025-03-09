package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.UserCreateRequestDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreateRequestDto dto){
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);
        return userRepository.save(user);
    }
}
