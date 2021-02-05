package com.exception_;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haoxl
 * @Title: SpringMVC源码中可知，DispatcherServlet中如果产生了异常，
 * 则接下来会在processDispatchResult()方法中查询当前容器中是否有HandlerExceptionResolver接口的实现类，
 * 如果有则调用它的resolveException()方法，得到返回的View，如果没有则使用框架默认的异常处理类
 * @Package
 * @Description:
 * @date 2021/2/5 17:23
 */
@Component
public class GlobalExceptionByView implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        //todo :需要返回错误页面展示的用这个中
        return new ModelAndView("/error");
    }
}
