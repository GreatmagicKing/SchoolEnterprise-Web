package com.school.config.security;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.school.config.security.handler.MyAuthenticationFailureHandler;
import com.school.config.security.handler.MyAuthenticationSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	UserDetailsService myUserDetailService;
	
	

    @Autowired
    private MyAuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler loginFailureHandler;
    
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(myUserDetailService)
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.formLogin()
		       .loginProcessingUrl("/login")
		       .usernameParameter("userId")
		       .passwordParameter("passWord")
//		       .successForwardUrl("/success")
//		       .failureForwardUrl("/failure")
		       .successHandler(loginSuccessHandler)
		       .failureHandler(loginFailureHandler)
		       .and()
		    .authorizeRequests()
//		       .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//		       .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		       
		       .antMatchers("/login").permitAll()
		       .antMatchers("/student").hasAuthority("1")
		       .antMatchers("/getStudentCourses").hasAuthority("1")
		       .antMatchers("/getQuestionList").hasAuthority("1")
		       .antMatchers("/setQuestionList").hasAuthority("1")
//		       .antMatchers("/getQuestionDetails").hasAuthority("1")
		       .antMatchers("/getQuestionDetails").hasAnyAuthority("1","2","3","4")
		       .antMatchers("/insertQuestionDetails").hasAnyAuthority("1","2")
		       .antMatchers("/getTestList").hasAuthority("1")
		       .antMatchers("/getTestDetails").hasAuthority("1")
		       .antMatchers("/setTestAnswer").hasAuthority("1")
		       .antMatchers("/teacher").hasAuthority("2")
		       .antMatchers("/getTeacherCourses").hasAuthority("2")
		       .antMatchers("/setTeacherCourses").hasAuthority("2")
		       .antMatchers("/getTeacherQuestion").hasAuthority("2")
		       .antMatchers("/getTeacherTest").hasAuthority("2")
		       .antMatchers("/showTestScore").hasAuthority("2")
		       .antMatchers("/releaseTest").hasAuthority("2")
		       .antMatchers("/revokeTest").hasAuthority("2")
		       .antMatchers("/getTeacherInterview").hasAuthority("2")
		       .antMatchers("/setTeacherInterview").hasAuthority("2")
		       .antMatchers("/getStudentRate").hasAnyAuthority("3","4")
		       .antMatchers("/getStudentDetails").hasAnyAuthority("3","4")
		       .antMatchers("/getHrQuestionList").hasAnyAuthority("3","4")
		       .antMatchers("/getHrAnswerList").hasAnyAuthority("3","4")
		       .antMatchers("/setMyInterview").hasAuthority("3")
		       .antMatchers("/getMyInterview").hasAuthority("3")
		       .antMatchers("/getEveryRecruit").hasAuthority("3")
		       .antMatchers("/getTwoRecruit").hasAuthority("3")
		       .antMatchers("/getHrRecruit").hasAuthority("3")
		       .antMatchers("/setHrRecruit").hasAuthority("3")
		       .antMatchers("/getTeacherRecruit").hasAuthority("3")
		       .antMatchers("/setTeacherRecruit").hasAuthority("3")
		       .antMatchers("/getInterviewR").hasAnyAuthority("3","4")
		       .antMatchers("/setInterviewR").hasAuthority("3")
//		       .antMatchers("/insertQuestionDetails").hasAuthority("2")
//		       .anyRequest().permitAll()
		       .and()
		    .csrf().disable()
		    ;
		http.cors();
//		http.addFilter(new JWTAuthenticationFilter(authenticationManager()))
//		http.addFilter(new JWTAuthorizationFilter(authenticationManager()))
        	// 不需要session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

}
