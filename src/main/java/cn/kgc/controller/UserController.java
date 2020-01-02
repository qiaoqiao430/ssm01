package cn.kgc.controller;

import cn.kgc.pojo.User;
import cn.kgc.service.UserService;
import cn.kgc.util.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sys")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/pwdmodify")
    public String passModify(){
        return "user/pwdmodify";
    }

    @RequestMapping("/savepwdmodify")
    public String doPwdModify(HttpSession session,String newpassword){
        User user = (User) session.getAttribute("user");
        boolean b = userService.modifyPwd(user.getId(), newpassword);
        if (b==true){
            return "redirect:/";
        }else {
            return "redirect:/sys/pwsmodify";
        }
    }
    @RequestMapping("/checkpwd")
    @ResponseBody
    public Data checkpwd(String oldpassword, HttpSession session){
        User user = (User)session.getAttribute("user");
        Data result= new Data();
        if (user == null){
            result.setResult("sessionerror");
        }else if (oldpassword == null && oldpassword.equals("")) {
            result.setResult("error");
        }else if (user.getUserPassword().equals(oldpassword)){
            result.setResult("true");
        }else {
            result.setResult("false");
        }
        return result;
    }
}
