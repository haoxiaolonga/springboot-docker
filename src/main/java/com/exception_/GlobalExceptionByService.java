package com.exception_;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haoxl
 * @Title: 通过ControllerAdvice +  ExceptionHandler 指定拦截异常作自定义处理
 * @Package
 * @Description:
 * @date 2021/2/5 16:47
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionByService {


    /**
     * 这里需要返回 ResponseEntity ，不然globalDispatcher 会再次拦截一次
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> selfException(HttpServletRequest request, Exception e) {

        log.error("请求{}出现{}异常，信息为:{}", request.getRequestURL(), e.getClass().getSimpleName(), e.getMessage());
        //todo: 这里可以封装为restful json对象
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
