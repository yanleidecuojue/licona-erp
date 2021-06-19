package plj.licona.club.gateway.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler(value = BusinessException.class)
    public ResultResponse customExceptionHandler(BusinessException e) {
        log.error(e.getMessage(), e);
        return ResultResponse.customException(e);
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