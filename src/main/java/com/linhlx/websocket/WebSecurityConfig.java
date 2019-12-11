package com.linhlx.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("laxuanlinh2")
                .password("$2y$12$Vd9tD.5/nqsBat0wf2gh4OIxQ4M2oye7XA.OXHmk0uLHVpTGfuP2i")
                .roles("user")
        .and()
                .withUser("laxuanlinh")
                .password("$2y$12$Vd9tD.5/nqsBat0wf2gh4OIxQ4M2oye7XA.OXHmk0uLHVpTGfuP2i")
                .roles("user");
    }
}
