package org.tylor.origin.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.AntPathMatcher;

/**
 * @author liubin10  2018/3/21
 */
public abstract class ExcludeFilter implements Filter {

    protected final static  String[] EXCLUDE_PATH = {"/login**","**/register/**","/login/**"};


    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    protected  List<String> excludedPaths;

    public ExcludeFilter() {
        if (excludedPaths==null){
            this.excludedPaths = Arrays.asList(EXCLUDE_PATH);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {
        if (shouldNotFilter((HttpServletRequest) servletRequest)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            doFilterIntern(servletRequest,servletResponse,filterChain);
        }

    }
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return excludedPaths.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

    @Override
    public void destroy() {

    }


    protected abstract void doFilterIntern(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException;
}
