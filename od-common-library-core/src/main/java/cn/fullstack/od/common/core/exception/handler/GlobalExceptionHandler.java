package cn.fullstack.od.common.core.exception.handler;

import cn.fullstack.od.common.core.exception.BadOperationException;
import cn.fullstack.od.common.core.exception.OperationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @date 2024/12/5
 */
@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OperationFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void operationFailedException() {

    }

    @ExceptionHandler(BadOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void badOperationException() {

    }

}
