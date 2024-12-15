package cn.fullstack.od.common.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @date 2024/12/6
 */
@Controller
public class SystemController {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, ModelMap modelMap) {
        modelMap.addAttribute("username", username);
        modelMap.addAttribute("password", password);
        return "index";
    }

}
