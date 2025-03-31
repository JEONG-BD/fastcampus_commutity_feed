package org.fastcampus.user.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.application.dto.UserCreateRequestDto;
import org.fastcampus.user.application.dto.UserGetResponseDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.jpa.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JpaUserListQueryRepository userListQueryRepository;
    @PostMapping
    public Response<Long> createUser(@RequestBody UserCreateRequestDto dto){
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId") Long userId){
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowerUserList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId") Long userId){
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowingUserList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}")
    public Response<UserGetResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId){
        UserGetResponseDto userProfile = userService.getUserProfile(userId);
        return Response.ok(userProfile);
    }

}
