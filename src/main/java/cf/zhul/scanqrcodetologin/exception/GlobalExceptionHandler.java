package cf.zhul.scanqrcodetologin.exception;

import cf.zhul.scanqrcodetologin.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhul
 * @date 2019/10/25 12:59
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        if (e.getBindingResult().getFieldError() != null) {
            return Result.fail(e.getBindingResult().getFieldError().getDefaultMessage());
        }
        return Result.fail("参数错误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return Result.fail("Required request body is missing, check if request method's application/json");
    }

    @ExceptionHandler(HttpBaseException.class)
    public Result<?> handleHttpMessageNotReadableException(HttpBaseException e) {
        return Result.fail(e);
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }
}