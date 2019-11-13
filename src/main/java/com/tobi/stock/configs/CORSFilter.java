package com.tobi.stock.configs;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 24/07/2019
 * Time: 11:35 AM
 **/

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "x-requested-with,x-auth-username,x-auth-password,x-auth-token," +
                        "origin,accept,content-type,access-control-request-method,access-control-request-headers,authorization");

        if (!request.getMethod().equals("OPTIONS")) {
            try {
                chain.doFilter(req, res);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
