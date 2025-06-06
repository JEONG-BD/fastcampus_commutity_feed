package org.fastcampus.admin.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.admin.ui.dto.GetTableListResponse;
import org.fastcampus.admin.ui.dto.posts.GetPostTableRequestDto;
import org.fastcampus.admin.ui.dto.posts.GetPostTableResponseDto;
import org.fastcampus.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.admin.ui.dto.users.GetUserTableResponseDto;
import org.fastcampus.admin.ui.query.AdminTableQueryRepository;
import org.fastcampus.admin.ui.query.UserStatsQueryRepository;
import org.fastcampus.post.ui.dto.GetPostResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStatsQueryRepository userStatsQueryRepository;
    private final AdminTableQueryRepository adminTableQueryRepository;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("result", userStatsQueryRepository.getDailyRegisterUserStates(7));
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView users(GetUserTableRequestDto dto){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");

        GetTableListResponse<GetUserTableResponseDto> result = adminTableQueryRepository.getUserTableDate(dto);

        modelAndView.addObject("requestDto", dto);
        modelAndView.addObject("userList", result.getTableDate());
        modelAndView.addObject("totalCount", result.getTotalCount());
        return modelAndView;
    }

    @GetMapping("/posts")
    public ModelAndView posts(GetPostTableRequestDto dto){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("posts");

        GetTableListResponse<GetPostTableResponseDto> result = adminTableQueryRepository.getPostTableDate(dto);

        modelAndView.addObject("requestDto", dto);
        modelAndView.addObject("postList", result.getTableDate());
        modelAndView.addObject("totalCount", result.getTotalCount());
        return modelAndView;
    }




}
