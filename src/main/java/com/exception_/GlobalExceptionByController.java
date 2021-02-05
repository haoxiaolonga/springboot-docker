package com.exception_;

import com.mongodb.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author haoxl
 * @Title: 使用@ControllerAdvice注解。
 * 继承ResponseEntityExceptionHandler类， 包括一些参数转换，请求方法不支持等等之类的异常都会被捕获。
 * 被捕获的原因是@ExceptionHandler标签，里面所有的异常类只要发生了，就会被这个方法所捕获
 * @Package
 * @Description:
 * @date 2021/2/5 16:32
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionByController extends ResponseEntityExceptionHandler {


    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.error("进入controller异常");
        return new ResponseEntity<Object>("请求出错了", HttpStatus.NOT_EXTENDED);
    }

}
