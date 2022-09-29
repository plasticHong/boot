package com.spring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class SecurityConfig {


    @Autowired
    CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    };

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http) throws Exception {

        //object create : builder pattern

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()   //parameter : http method enable
                .antMatchers("/permitAll/*").permitAll()
                .antMatchers("/member/join").permitAll()
                .antMatchers("/board/*").authenticated()
                .antMatchers("/hasRole_User/*").hasRole("USER")
                .antMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/member/login")  // request url
                .loginProcessingUrl("/securityLogin")   //action url
                .usernameParameter("id")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true) //alwaysUse : default false
                .permitAll()
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
            .and()
                .userDetailsService(userDetailsService);

        return http.build();
    }


    @Bean
    @Order(0)
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        // ignoring
        return http.requestMatchers(matchers -> matchers
                        .antMatchers("/css/**", "/js/**"))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll())
                .requestCache(RequestCacheConfigurer::disable)
                .securityContext(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .build();
    }




}
