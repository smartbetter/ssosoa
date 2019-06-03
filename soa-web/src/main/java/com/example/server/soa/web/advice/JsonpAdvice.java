package com.example.server.soa.web.advice;

import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 支持跨域请求的Jsonp数据
 */
@SuppressWarnings("deprecation")
@ControllerAdvice
public class JsonpAdvice extends FastJsonpResponseBodyAdvice {

    public JsonpAdvice() {
        super("callback", "jsonp");
    }
}
