package cn.xl.wx.wx1901.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author luowenqin
 * @create 2019-03-12 15:57
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
