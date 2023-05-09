package pers.lintao.eorp.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import pers.lintao.eorp.dto.EmployeeDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 登录拦截器
 * @author: lilintao@163.com
 * @time: 2023/4/4 16时13分 周二
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求被拦截的URI:"+request.getRequestURI());
        //从session去查找用户信息
        Object userObj = request.getSession().getAttribute("userInfo");
        //如果用户信息不为空,就将其装换为EmployeeDTO对象
        if (null != userObj){
            //不为空才能强转
            EmployeeDTO userInfo =(EmployeeDTO) userObj;
            log.info("session中的userInfo不为空,userInfo="+userInfo.toString());
            //确保员工id不为空且是在职状态
            if (userInfo.getEmId() != null && userInfo.getStatus() == 1){
                //如果用户已经登录,用户这时的url是/或者/toLogin,就主动跳转至/main(现在不起作用,因为配置文件放行了)
                if(request.getRequestURI().toLowerCase().indexOf("toLogin")>=0 || "/".equals(request.getRequestURI())){
                    response.sendRedirect("/main");
                    return false;
                }
                return true;
            }
        }
        //如果没有获取到员工信息,且请求路径是/toLogin和/,是要放行的(配置拦截器的时候直接放行)
        if(request.getRequestURI().toLowerCase().indexOf("toLogin")>=0 || "/".equals(request.getRequestURI())){
            return true;
        }
        //如果没有获取到员工信息,不让直接访问/main页面,就重定向到登陆界面
        response.sendRedirect("/toLogin");
        return false;
    }
}
