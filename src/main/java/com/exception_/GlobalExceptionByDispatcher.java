package com.exception_;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haoxl
 * @Title: 全局处理未到controller 那层的异常
 * @Package
 * @Description:
 * @date 2021/2/5 16:14
 */
@RestController
public class GlobalExceptionByDispatcher implements ErrorController {

    /**
     * 例如404错误，dispatcher 找不到对应的处理器
     * @return
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Object dealError(HttpServletRequest request, HttpServletResponse response){

        //todo: 异常处理逻辑
        return "service error";
    }

}
