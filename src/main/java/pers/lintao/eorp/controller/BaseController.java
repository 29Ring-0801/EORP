package pers.lintao.eorp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: Controller的基础类,目的是让所有基础该类的子类可以直接使用Request对象
 * @author: lilintao@163.com
 * @time: 2023/4/24 15时20分 周日
 */
@Controller
public class BaseController {

    /**
     *   多个请求不会共享一个Request对象,因为SpringMvc内部使用了ThreadLocal
     */
    @Autowired
    HttpServletRequest request;

    @ExceptionHandler
    public String exception(HttpServletRequest request, Exception ex)  {
        request.setAttribute("ex", ex);
        // 根据不同错误转向不同页面，即异常与View的对应关系
//        if(ex instanceof SQLException) {
//            return "sql-error";
//        }else if(ex instanceof MyException) {
//            return "my-error";
//        } else {
//            return "error";
//        }
        return "error";
    }


}
