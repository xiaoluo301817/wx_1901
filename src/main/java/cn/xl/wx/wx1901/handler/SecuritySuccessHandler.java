package cn.xl.wx.wx1901.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luowenqin
 * @create 2019-03-12 13:16
 */
@Component
public class SecuritySuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {




    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        String contextPath = request.getContextPath();

        response.setContentType("application/json;UTF-8");
        response.sendRedirect(contextPath+"/main/gotoIndex");
    }
}
