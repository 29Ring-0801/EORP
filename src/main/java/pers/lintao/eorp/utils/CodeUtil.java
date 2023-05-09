package pers.lintao.eorp.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 对验证码进行验证
 * @author: lilintao@163.com
 * @time: 2023/4/25 11时53分 周一
 */
public class CodeUtil {

    /**
     * 进行验证
     * @param request 本次请求的request对象,通过request获取实际生成的验证码
     * @param inCaptcha 用户输入的验证码
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request,String inCaptcha){
        //生成的验证码
        String captcha = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //对验证码进行判断
        if (captcha == null || captcha == "" || !captcha.equals(inCaptcha)){
            return false;
        }
        return true;
    }


}
