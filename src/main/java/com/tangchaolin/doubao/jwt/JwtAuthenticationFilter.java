package com.tangchaolin.doubao.jwt;


import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JwtAuthenticationFilter implements Filter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();



    private boolean isProtectedUrl(HttpServletRequest request) {
        List<String> protectedPaths = new ArrayList<String>();
        protectedPaths.add("/ums/user/info");
        protectedPaths.add("/post/create");
        protectedPaths.add("/post/update");
        protectedPaths.add("/post/delete/*");
        protectedPaths.add("/comment/add_comment");
        protectedPaths.add("/relationship/subscribe/*");
        protectedPaths.add("/relationship/unsubscribe/*");
        protectedPaths.add("/relationship/validate/*");
        boolean bFind = false;
        for( String passedPath : protectedPaths ) {
            bFind = pathMatcher.match(passedPath, request.getServletPath());
            if( bFind ) {
                break;
            }
        }
        return bFind;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        String name ;
        try {
            if(isProtectedUrl(request)) {
//                System.out.println(request.getMethod());
                if(!request.getMethod().equals("OPTIONS"))
                    request = JwtUtil.validateTokenAndAddUserIdToHeader(request);

            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        name= request.getHeader("userName");
        filterChain.doFilter(request, response);
    }



    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}