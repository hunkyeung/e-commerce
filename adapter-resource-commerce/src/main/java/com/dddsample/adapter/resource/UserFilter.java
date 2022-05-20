package com.dddsample.adapter.resource;

import com.robustel.ddd.service.ThreadLocalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author YangXuehong
 * @date 2021/7/13
 */
@Component
@WebFilter(filterName = "user", urlPatterns = {"/*"})
public class UserFilter implements Filter {
    @Value("${robustel.user-id}")
    private String USER_ID;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String user_id = req.getHeader(USER_ID);
        if (!StringUtils.isBlank(user_id)) {
            ThreadLocalUtil.set(USER_ID, user_id);
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            ThreadLocalUtil.remove(USER_ID);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
