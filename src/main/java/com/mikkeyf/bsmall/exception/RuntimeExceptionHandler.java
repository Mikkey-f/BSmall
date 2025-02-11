package com.mikkeyf.bsmall.exception;

import com.mikkeyf.bsmall.enums.ResponseEnum;
import com.mikkeyf.bsmall.result.Result;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.exception
 * @className: RuntimeExceptionHandler
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/4 23:20
 * @version: 1.0
 */
@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result handle(RuntimeException e) {
        return Result.error(ResponseEnum.ERROR.getData());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result notValidException(RuntimeException e) {
        return Result.error(ResponseEnum.PARAM_ERROR.getData());
    }
}
