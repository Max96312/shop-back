package max.shop.controller.api;

import lombok.RequiredArgsConstructor;
import max.shop.dto.request.user.UserLoginForm;
import max.shop.dto.request.user.UserRegisterForm;
import max.shop.dto.UserAccountDto;
import max.shop.dto.response.UserLoginResponse;
import max.shop.dto.response.UserRegisterResponse;
import max.shop.response.ResponseService;
import max.shop.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 정보 수정
 * 회원 탈퇴
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final ResponseService responseService;

    /**
     * 회원 가입
     */
    @PostMapping("/sign/register")
    public UserRegisterResponse register(@RequestBody @Validated UserRegisterForm form
//                                    , BindingResult bindingResult
    ) {
//        if (bindingResult.hasFieldErrors("email"))
//            throw new EmailPatternException();
//        if(bindingResult.hasFieldErrors("password"))
//            throw new PasswordPatternException();
        UserAccountDto savedUser = userService.join(form);
        return new UserRegisterResponse(savedUser.getUserId());
    }

    /**
     * 로그인
     */
    @PostMapping("/sign/login")
    public UserLoginResponse login(@RequestBody UserLoginForm form) {
        UserAccountDto loginUser = userService.loginUser(form);
        return new UserLoginResponse(loginUser.getUserId(), loginUser.getName());
    }
}
