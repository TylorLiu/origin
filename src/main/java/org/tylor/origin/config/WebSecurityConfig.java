package org.tylor.origin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/webjars/**", "/register","/register/**", "/login").permitAll()
//            .antMatchers("/swagger-ui.html").hasRole("ADMIN")
            .anyRequest().authenticated()
            //登陆设置
            .and().formLogin().loginPage("/login").permitAll()
            //登出设置
            .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
            .clearAuthentication(true);
//        http
//            .authorizeRequests()
//                .antMatchers("/webjars/**", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//            .logout()
//                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}