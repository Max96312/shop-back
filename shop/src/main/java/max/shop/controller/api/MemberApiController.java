package max.shop.controller.api;

import lombok.RequiredArgsConstructor;
import max.shop.common.exception.EmailPatternException;
import max.shop.common.exception.PasswordPatternException;
import max.shop.dto.request.member.MemberLoginForm;
import max.shop.dto.request.member.MemberRegisterForm;
import max.shop.dto.response.SingleResult;
import max.shop.dto.response.member.MemberLoginResponse;
import max.shop.dto.response.member.MemberRegisterResponse;
import max.shop.service.response.ResponseService;
import max.shop.serviceImp.MemberService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberApiController {
    private final MemberService memberService;
    private final ResponseService responseService;

    /**
     * 회원 가입
     */
    @PostMapping("/join")
    public SingleResult<MemberRegisterResponse> register(@RequestBody @Validated MemberRegisterForm form
                                    , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("email"))
            throw new EmailPatternException();
        if(bindingResult.hasFieldErrors("password"))
            throw new PasswordPatternException();

        return responseService.handleSingleResult(memberService.join(form));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public SingleResult<MemberLoginResponse> login(@RequestBody MemberLoginForm form) {
        return responseService.handleSingleResult(memberService.login(form));
    }

    /**
     * 회원 정보 수정
     * - 이름 변경
     * - 전화 번호 수정
     * - 주소 변경 : api 분리
     */


    /**
     * 회원 탈퇴
     */
}
