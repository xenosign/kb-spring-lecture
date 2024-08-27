package org.example.controller.user;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final String context = "/user";

    @GetMapping("/register")
    public String registerPage() {
        return context + "/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam String password, Model model) {
        if (username.isEmpty() || password.isEmpty()) {
            model.addAttribute("errMsg", "아이디 또는 비밀번호가 누락 되었습니다");
            return context + "/register-failed";
        }

        User user = userService.findByUsername(username);
        if (user != null) {
            model.addAttribute("errMsg", "동일한 ID 를 가지는 사용자가 존재합니다");
            return context + "/register-failed";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userService.save(newUser);

        model.addAttribute("username", username);

        return context + "/register-success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return context + "/login";
    }

    @GetMapping("/login-security")
    public String loginSecurityPage() {
        return context + "/security/login-security";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.findByUsername(username);

        if (user == null) {
            model.addAttribute("errMsg", "해당 id의 사용자가 없습니다");
            return context + "/login-failed";
        }

        if (!userService.isPasswordValid(user, password)) {
            model.addAttribute("errMsg", "비밀 번호가 틀립니다");
            return context + "/login-failed";
        }

        model.addAttribute("username", username);
        session.setAttribute("loginUser", user);
        return context + "/login-success";
    }

//    @GetMapping("/member")
//    public String loginSuccessPage(Model model, Principal principal) {
//        if (principal != null) {
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(principal.getName());
//            model.addAttribute("user", userDetails);
//        }
//        return context + "/member";
//    }

    @GetMapping("/login-failed")
    public String loginFailPage() {
        return context + "/login-failed2";
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/user/logout";
    }

    @GetMapping("/admin")
    public String admin(HttpSession session) {
        return "/user/admin";
    }
}