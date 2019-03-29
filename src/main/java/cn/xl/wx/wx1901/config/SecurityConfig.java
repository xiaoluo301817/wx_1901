package cn.xl.wx.wx1901.config;

import cn.xl.wx.wx1901.filter.ValidateCodeFilter;
import cn.xl.wx.wx1901.handler.SecurityFailHandler;
import cn.xl.wx.wx1901.handler.SecuritySuccessHandler;
import cn.xl.wx.wx1901.service.impl.MyUserDetailsService;
import org.apache.activemq.broker.region.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 *
 *
 * 安全认证处理类
 * @author luowenqin
 * @create 2019-03-12 8:58
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder ();
    }



    @Autowired
    private SecuritySuccessHandler securitySuccessHandler;


    @Autowired
    private SecurityFailHandler securityFailHandler;


    @Autowired
    MyUserDetailsService myUserDetailsService;







    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter =new ValidateCodeFilter();
        validateCodeFilter.setSecurityFailHandler(securityFailHandler);
        http.
                addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class). //验证密码和用户名执勤对图形马进行验证
                formLogin().                                        //表单请求
                loginPage("/login.html").                           //登录页
                loginProcessingUrl("/authentication/form").
                successHandler(securitySuccessHandler).            //登录成功后处理
                failureHandler(securityFailHandler).               //登录失败后处理
                and().
                authorizeRequests().                        //请求授权
                antMatchers("/login.html","/admin/code").permitAll().   //对相对应的请求放行
                anyRequest().                               // 任何请求
                authenticated()
                .and().
                csrf().
                disable().
                headers().
                frameOptions().
                disable();                            // 都需要授权
        http.logout();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//    }
}
