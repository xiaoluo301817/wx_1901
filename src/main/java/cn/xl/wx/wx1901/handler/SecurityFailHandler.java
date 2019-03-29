package cn.xl.wx.wx1901.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luowenqin
 * @create 2019-03-12 13:35
 */

@Component(value = "securityFailHandler")
public class SecurityFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.info("异常信息："+exception.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().print(objectMapper.writeValueAsString(exception.getMessage()));  //获取相应的错误信息
//        request.getRequestDispatcher("/login.html").forward(request,response);
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request,response,exception);

    }
}
