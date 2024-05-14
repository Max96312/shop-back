package max.shop.controller.api;

import lombok.RequiredArgsConstructor;
import max.shop.common.exception.EmailPatternException;
import max.shop.common.exception.PasswordPatternException;
import max.shop.dto.request.user.UserRegisterForm;
import max.shop.dto.response.UserResponse;
import max.shop.response.ResponseService;
import max.shop.response.SingleResult;
import max.shop.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final ResponseService responseService;

    @PostMapping("/sign/register")
    public UserResponse register(@RequestBody @Validated UserRegisterForm form
//                                    , BindingResult bindingResult
    ) {
//        if (bindingResult.hasFieldErrors("email"))
//            throw new EmailPatternException();
//        if(bindingResult.hasFieldErrors("password"))
//            throw new PasswordPatternException();

        return userService.join(form);
    }
}
