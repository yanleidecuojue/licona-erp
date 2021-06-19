package plj.licona.club.uaa.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import plj.licona.club.uaa.response.ResultEnum;
import plj.licona.club.uaa.response.ResultResponse;

/**
 * 全局异常处理器
 *
 * @author licona
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public ResultResponse customExceptionHandler(InternalAuthenticationServiceException e) {
        log.error(e.getMessage(), e);
        return new ResultResponse(405, "用户名错误", null);
    }

    @ExceptionHandler(value = InvalidGrantException.class)
    public ResultResponse usernameNotFoundException(InvalidGrantException e) {
        return new ResultResponse(406, "用户密码错误", null);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResultResponse otherExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResultResponse.otherException(ResultEnum.FAILED);
    }
}