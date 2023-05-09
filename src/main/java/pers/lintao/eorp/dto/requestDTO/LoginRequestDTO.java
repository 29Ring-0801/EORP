package pers.lintao.eorp.dto.requestDTO;

import java.io.Serializable;

/**
 * @description:
 * @author: lilintao@163.com
 * @time: 2023/4/24 10时24分 周日
 */
public class LoginRequestDTO implements Serializable {
    private static final long serialVersionUID = 9101883586413104756L;

    private String username;

    private String password;

    private Boolean needVerify;

    private String inCaptcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getNeedVerify() {
        return needVerify;
    }

    public void setNeedVerify(Boolean needVerify) {
        this.needVerify = needVerify;
    }

    public String getInCaptcha() {
        return inCaptcha;
    }

    public void setInCaptcha(String inCaptcha) {
        this.inCaptcha = inCaptcha;
    }

    @Override
    public String toString() {
        return "LoginRequestDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", needVerify=" + needVerify +
                ", inCaptcha='" + inCaptcha + '\'' +
                '}';
    }
}
