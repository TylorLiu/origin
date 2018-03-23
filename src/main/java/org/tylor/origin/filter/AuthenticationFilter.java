package org.tylor.origin.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author liubin10  2018/3/20
 */
public class AuthenticationFilter extends ExcludeFilter {


    @Override
    protected void doFilterIntern(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        if (session == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
            return;
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
