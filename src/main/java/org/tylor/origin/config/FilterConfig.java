package org.tylor.origin.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tylor.origin.filter.AuthenticationFilter;

/**
 * @author liubin10  2018/3/20
 */
@Configuration
public class FilterConfig {

    @Bean(name = "authenticationFilter")
    public FilterRegistrationBean authenticationFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(
            new AuthenticationFilter());
        registration.addUrlPatterns("*");
        return registration;
    }
}
