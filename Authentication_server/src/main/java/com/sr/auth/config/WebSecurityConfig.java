package com.sr.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.sr.auth.repository.*;
import com.sr.auth.service.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import javax.sql.DataSource;
import org.springframework.http.HttpMethod;
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
//    @Autowired
//    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
//        // @formatter:off
//	auth.inMemoryAuthentication()
//	  .withUser("john").password("123").roles("USER").and()
//	  .withUser("tom").password("111").roles("ADMIN").and()
//	  .withUser("user1").password("pass").roles("USER").and()
//	  .withUser("admin").password("nimda").roles("ADMIN");
//    }// @formatter:on

	
	@Autowired private UserRepository userRepository;

	@Autowired
    private DataSource dataSource;
	
	//user details and roles from Mysql
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
        .usersByUsernameQuery("select * from user where user_name=?")
        .authoritiesByUsernameQuery("select u.user_name,r.role from user u inner join user_roles ur on(u.id=ur.user_id) inner join role r on(ur.role_id=r.id)  where u.user_name=?")
        .dataSource(dataSource);
    	
    	
    	
    	auth.userDetailsService(userDetailsServiceBean());
        
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(userRepository);
    }
	
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		http
		.authorizeRequests().antMatchers("/greeting").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
		//all requests to require login
		.anyRequest().authenticated()
        .and()
        .formLogin();
        //.and()
        //.httpBasic();
		
		// @formatter:on
    }
}
