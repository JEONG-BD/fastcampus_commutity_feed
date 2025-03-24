package org.fastcampus.user.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

}
