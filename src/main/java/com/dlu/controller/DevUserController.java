package com.dlu.controller;


import com.dlu.pojo.DevUser;
import com.dlu.service.AppInfoService;
import com.dlu.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dev")
public class DevUserController {

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private DevUserService devUserService;



    @RequestMapping("/toLogin")
    public String toLogin(){
        return "dev/login";
    }

    @RequestMapping("/login")
    public String login(DevUser devUser, Model model, HttpSession session){
        devUser = devUserService.login(devUser);
        if (devUser !=null){
            //登陆成功
            session.setAttribute("devUser",devUser);
            return "dev/index";
        }else {
            //登录失败
            model.addAttribute("errorMsg","登陆失败");
            return "dev/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("devUser");
        httpSession.invalidate();
        return "redirect:/";
    }




}
