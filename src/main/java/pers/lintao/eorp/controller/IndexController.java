package pers.lintao.eorp.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import pers.lintao.eorp.dto.EmployeeDTO;
import pers.lintao.eorp.dto.requestDTO.LoginRequestDTO;
import pers.lintao.eorp.service.EmployeeService;
import pers.lintao.eorp.utils.CodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @description: 登录登出
 * @author: lilintao@163.com
 * @time: 2023/4/23 20时16分 周六
 */
@Slf4j
@Controller
public class IndexController extends BaseController{

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Producer producer;

    @GetMapping(value = {"/","/toLogin"})
    public String login(){
        log.info("进入--login--方法...");
        return "login";
    }

    /**
     *  获取验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/Kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("进入--getKaptchaImage--方法...");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = producer.createText();
        log.info("******************当前验证码为：{}******************", capText);
        // 将验证码存于session中
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // 向页面输出验证码
        ImageIO.write(bi, "jpg", out);
        try {
            // 清空缓存区
            out.flush();
        } finally {
            // 关闭输出流
            out.close();
        }
    }

    @PostMapping(value = "/checkLogin")
    @ResponseBody
    public Map<String,Object> checkLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        log.info("进入--checkLogin--方法,入参LoginRequestDTO={}",loginRequestDTO.toString());
        Map<String,Object> modelMap = new HashMap<>();
        //是否需要进行验证码的验证
        Boolean needVerify = loginRequestDTO.getNeedVerify();
        if (needVerify && !CodeUtil.checkVerifyCode(request,loginRequestDTO.getInCaptcha())){
            //需要验证,且验证不通过
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码填写错误");
            return modelMap;
        }
        //获取用户输入的用户名
        String username = loginRequestDTO.getUsername();
        //获取用户输入的密码
        String password = loginRequestDTO.getPassword();

        if (username != null && password != null){
            //通过用户名和密码去查库
            EmployeeDTO userInfo = employeeService.getEmInfoByUserNamePassword(username, password);
            if (null != userInfo){
                //if根据账号密码查询到的对象不null,则代表登陆成功
                modelMap.put("success", true);
                modelMap.put("username", userInfo.getName());
                //同时将用户信息存入session
                request.getSession().setAttribute("userInfo", userInfo);
            } else {
                //根据用户名和密码查不到
                modelMap.put("success", false);
                modelMap.put("errMsg", "用户名密码错误");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名或密码不能为空");
        }
        return modelMap;
    }

    @GetMapping(value = "/main")
    public String toMain(){
        log.info("进入--toMain--方法...");
        request.getSession().setAttribute("pageName","后台首页");
        return "main";
    }

    @GetMapping(value = "/toPersonalInfo")
    public String toPersonalInfo(){
        log.info("进入--toPersonalInfo--方法...");
        request.getSession().setAttribute("pageName","个人信息");
        return "personal-info";
    }

    @GetMapping(value = "/toEditPwd")
    public String toEditPwd(){
        log.info("进入--toEditPwd--方法...");
        request.getSession().setAttribute("pageName","修改密码");
        return "edit-password";
    }

    @PostMapping("/logout")
    @ResponseBody
    public Map<String,Object> logout(){
        log.info("进入--logout--方法...");
        Map<String,Object> modelMap = new HashMap<>();
        request.getSession().setAttribute("userInfo", null);
        modelMap.put("success", true);
        return modelMap;
    }

    @PostMapping("/i18n")
    public String I18n(String language){
        log.info("进入--I18n--方法,入参language="+language);
        if ("中文".equals(language)){
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.CHINA);
        }else {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
        }
        return "login";
    }


}
