package com.jason.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       try{
           // "traceId" 正常来说这个应该是前端传给后端得 这个只是作为后端demo得演示
           String requestNo = httpServletRequest.getHeader("request_no");
           log.info("-----输出：requesetNo,{}",requestNo);
           System.out.println("输出："+requestNo);
           MDC.put("traceid", getTraceId());
           filterChain.doFilter(httpServletRequest, httpServletResponse);
       }finally {
           /**注意  最后要执行 MDC.remove（）这个方法移除 */
           MDC.remove("traceid");
       }

    }

    private String getTraceId() {
        long timestamp = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String uniqueId = timestamp + uuid.toString().replace("-", "");
        return uniqueId;
    }
}

