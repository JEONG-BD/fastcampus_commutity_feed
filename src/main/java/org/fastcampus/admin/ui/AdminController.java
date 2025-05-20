package org.fastcampus.admin.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    //@GetMapping("/index")
    //public ModelAndView index() {
    //    ModelAndView modelAndView = new ModelAndView();
    //    modelAndView.setViewName("index");
    //    return modelAndView;
    //
    //}

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index"); // templates/index.html 렌더링
    }


}
