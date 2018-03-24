package org.tylor.origin.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tylor.origin.common.ClientException;
import org.tylor.origin.common.ServerResponse;
import org.tylor.origin.pojo.UserInfo;
import org.tylor.origin.service.UserService;

/**
 * @author liubin10  2018/3/24
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/register")
    public ModelAndView register(){
        return new ModelAndView("/register");
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ServerResponse<Void> register(@ModelAttribute("form") UserInfo userInfo) {

        Optional<UserInfo> option = userService.findUserByUsername(userInfo.getUsername());
        option.ifPresent(user ->{
           throw new ClientException("用户已存在");
        } );
        userService.save(userInfo);

        return new ServerResponse<>();

    }
}
