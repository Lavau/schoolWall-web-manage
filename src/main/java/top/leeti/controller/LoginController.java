package top.leeti.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/admin/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin/login/error")
    public String loginError(RedirectAttributes attributes){
        attributes.addFlashAttribute("msg", "登录失败");
        return "redirect:/admin/login";
    }

    @PostMapping("/admin/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            try{
                currentUser.login(token);
                session.setAttribute("username", username);
            } catch(UnknownAccountException unknownAccountException){
                attributes.addFlashAttribute("msg", "用户信息不存在！");
                return "redirect:/admin/login";
            } catch(
                    IncorrectCredentialsException incorrectCredentialsException){
                attributes.addFlashAttribute("msg", "密码错误！");
                return "redirect:/admin/login";
            }
        }
        return "my-index";
    }

    @GetMapping("/admin/login/out")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/admin/login";
    }
}
