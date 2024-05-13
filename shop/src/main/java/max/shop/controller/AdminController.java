package max.shop.controller;

import lombok.RequiredArgsConstructor;
import max.shop.service.item.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
