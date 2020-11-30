package projekti;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import projekti.user.UserService;

@Controller
public class DefaultController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal != null)
            model.addAttribute("auth", userService.findByHandle(principal.getName()));
        return "index.html";
    }
    
    @GetMapping("/**/{path:[^\\.]*[^.]*}")
    public String forward() {
        return "forward:/";
    }
}
