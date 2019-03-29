package cn.xl.wx.wx1901.filter;

import cn.xl.wx.wx1901.exception.ValidateCodeException;
import cn.xl.wx.wx1901.handler.SecurityFailHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *
 * 图形验证码请求验证
 * @author luowenqin
 * @create 2019-03-12 15:40
 */


public class ValidateCodeFilter extends OncePerRequestFilter {


    @Autowired
    private SecurityFailHandler securityFailHandler;


    public SecurityFailHandler getSecurityFailHandler() {
        return securityFailHandler;
    }

    public void setSecurityFailHandler(SecurityFailHandler securityFailHandler) {
        this.securityFailHandler = securityFailHandler;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        Logger logger = LoggerFactory.getLogger(getClass());


        String contextPath = request.getContextPath();
        if (StringUtils.equals(contextPath+"/authentication/form", request.getRequestURI())
                && StringUtils.equals(request.getMethod(), "POST")
                ) {
            try {
                validate(request);
            } catch (ValidateCodeException e) {
                securityFailHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }


    public void validate(HttpServletRequest request) {
        String sessionCode = (String) request.getSession().getAttribute("CODE");  //从session中获取参数
        String requestCode = request.getParameter("code");


        System.out.println("sessionCode="+sessionCode);
        System.out.println("requestCode="+requestCode);

        if (StringUtils.isBlank(requestCode))throw  new  ValidateCodeException("请输入验证码!");
        if (StringUtils.isBlank(sessionCode))throw  new  ValidateCodeException("当前的验证码不存在");
        if(!StringUtils.equals(requestCode,sessionCode))throw  new  ValidateCodeException("验证码不匹配");
        request.removeAttribute("CODE");
    }

}
