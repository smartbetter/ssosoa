package com.example.server.soa.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
@Slf4j
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * step 1、在Controller处理请求之前被调用
     *
     * @param handler 被拦截请求对象实例
     * @return        false-表示拦截当前请求,请求被终止, true-表示不拦截当前请求,请求被继续
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    /**
     * step 2、在Controller处理请求之后被调用,生成视图之前执行的动作
     *
     * @param handler      被拦截请求对象实例
     * @param modelAndView 可通过modelAndView改变显示的视图或修改发往视图的方法,比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * step 3、在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 注意: 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion方法
     * @param handler 被拦截请求对象实例
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception e) throws Exception {
        Long start = (Long) request.getAttribute("startTime");
        log.info("本次请求耗时: {}", (System.currentTimeMillis()-start));
    }
}
