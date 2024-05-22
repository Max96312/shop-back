package max.shop.common.advice;

import lombok.RequiredArgsConstructor;
import max.shop.common.exception.*;
import max.shop.dto.response.Result;
import max.shop.service.response.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "max.shop.controller")
public class ExceptionAdvice {
    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result defaultException() {
        return responseService.handleFailResult(500, "오류가 발생 하였습니다.");
    }

    @ExceptionHandler(UserNameDuplicatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Result userAccountAlreadyExist() {
        return responseService.handleFailResult(409, "입력하신 이메일이 이미 존재합니다.");
    }

    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result loginFail() {
        return responseService.handleFailResult(400, "이메일 또는 비밀번호를 잘못 입력하였습니다.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result userNotfound() {
        return responseService.handleFailResult(500, "유저를 찾을 수 없습니다.");
    }

    @ExceptionHandler(EmailPatternException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result failInputEmail() {
        return responseService.handleFailResult(400, "email형식의 회원가입만 가능합니다.");
    }

    @ExceptionHandler(PasswordPatternException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result failInputPassword() {
        return responseService.handleFailResult(400, "password형식이 올바르지 않습니다.");
    }

    @ExceptionHandler(DeliveryCompException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result failOrderCancelDeliveryComp() {
        return responseService.handleFailResult(400, "배송이 완료된 주문은 취소가 불가능합니다.");
    }

    @ExceptionHandler(NotEnoughStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result failOrderNotEnoughStock() {
        return responseService.handleFailResult(400, "");
    }


    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result itemNotFound() {
        return responseService.handleFailResult(500, "상품을 찾을 수 없습니다.");
    }
}
