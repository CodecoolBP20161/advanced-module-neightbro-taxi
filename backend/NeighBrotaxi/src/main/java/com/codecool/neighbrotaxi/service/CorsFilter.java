package com.codecool.neighbrotaxi.service;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        if (httpServletRequest.getHeader("Access-Control-Request-Method") != null
                && "OPTIONS".equals(httpServletRequest.getMethod())) {
            // CORS "pre-flight" httpServletRequest
            httpServletResponse.addHeader("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE");
            httpServletResponse.addHeader("Access-Control-Allow-Headers",
                    "X-Requested-With,Origin,Content-Type, Accept");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
