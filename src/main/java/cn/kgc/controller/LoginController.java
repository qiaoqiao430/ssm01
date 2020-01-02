package cn.kgc.controller;

import cn.kgc.pojo.User;
import cn.kgc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/user")
public class LoginController {

    @Resource
    private UserService userService;

    /*@RequestMapping("/login")
    public String login(){
        return "login";
    }*/

    @RequestMapping("/login")

    public String doLogin(HttpSession session, Model model, String userCode, String password){
        User user = userService.login(userCode, password);
        if (user == null){
            session.setAttribute("error","用户名或密码错误！");
            return "login";
        }
        session.setAttribute("user",user);
        return "redirect:/frame";
    }

    @RequestMapping("/frame")
    public String list(){
        return "frame";
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "login";
    }
}
