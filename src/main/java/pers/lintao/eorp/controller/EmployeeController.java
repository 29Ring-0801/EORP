package pers.lintao.eorp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lintao.eorp.dto.EmployeeDTO;
import pers.lintao.eorp.dto.requestDTO.EmployeeListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.EmployeeModifyPwdRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ToggleEmployeeRequestDTO;
import pers.lintao.eorp.dto.responseDTO.EmployeeResponseDTO;
import pers.lintao.eorp.entity.Employee;
import pers.lintao.eorp.service.EmployeeService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 员工管理模块
 * @author: lilintao@163.com
 * @time: 2023/4/10 11时21分 周二
 */
@Slf4j
@Controller
@RequestMapping(path = "/employee")
public class EmployeeController extends BaseController{

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/toList")
    public String toList(){
        log.info("进入--toList--方法...");
        request.getSession().setAttribute("pageName","员工管理");
        return "employee-list";
    }

    @GetMapping("/toAddEmployee")
    public String toAddEmployee(){
        log.info("进入--toAddEmployee--方法...");
        request.getSession().setAttribute("pageName","新增员工");
        return "employee-add";
    }

    @GetMapping("/goEmployee")
    public String goEmployee(){
        log.info("进入--goEmployee--方法...");
        request.getSession().setAttribute("pageName","员工详情");
        return "employee-info";
    }

    @GetMapping("/goEmployeeEdit")
    public String goEmployeeEdit(){
        log.info("进入--goEmployeeEdit--方法...");
        request.getSession().setAttribute("pageName","修改员工信息");
        return "employee-edit";
    }

    @PostMapping("/getList")
    @ResponseBody
    public Map<String,Object> getList(@RequestBody EmployeeListRequestDTO requestDTO){
        log.info("进入--getList--方法,入参EmployeeListRequestDTO="+requestDTO.toString());
        Map<String,Object> map = null;
        try {
            //调用职位的service层查询数据
            map = employeeService.queryPageList(requestDTO);
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/insertEmployee")
    @ResponseBody
    public Map<String,Object> insertEmployee(@RequestParam String employeeStr){
        log.info("进入--insertEmployee--方法,入参String="+employeeStr);
        Map<String,Object> map = new HashMap<>();
        //将接收的JSON字符串转换为对象
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = null;
        try {
            employee = mapper.readValue(employeeStr, Employee.class);
        } catch (Exception e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", "传入的JSON字符串有问题");
            return map;
        }
        try {
            //调用service层的新增员工方法
            employeeService.insert(employee);
        }catch (Exception e){
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        //响应给前端的信息
        map.put("success", true);
        return map;
    }

    @PostMapping("/toggleEmployeeStatus")
    @ResponseBody
    public Map<String,Object> toggleEmployeeStatus(@RequestBody ToggleEmployeeRequestDTO requestDTO){
        log.info("进入--toggleEmployeeStatus--方法,入参ToggleEmployeeRequestDTO="+requestDTO.toString());
        Map<String,Object> map = null;
        try {
            //非空校验
            map = employeeService.toggleStatus(requestDTO);
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/queryEmployeeById")
    @ResponseBody
    public Map<String,Object> queryEmployeeById(Integer employeeId) {
        log.info("进入--queryEmployeeById--方法,入参Integer={}",employeeId);
        Map<String, Object> map = new HashMap<>();
        EmployeeDTO userInfo = (EmployeeDTO) request.getSession().getAttribute("userInfo");
        EmployeeResponseDTO employeeResponseDTO = null;
        try {
            if (employeeId == null) {
                employeeId = userInfo.getEmId();
            }
            employeeResponseDTO = employeeService.queryById(employeeId);
        } catch (Exception e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        map.put("data", employeeResponseDTO);
        return map;
    }

    @PostMapping("/editEmployeeById")
    @ResponseBody
    public Map<String,Object> editEmployeeById(@RequestParam String employeeStr) {
        log.info("进入--editEmployeeById--方法,入参String="+employeeStr);
        Map<String,Object> map = new HashMap<>();
        EmployeeDTO userInfo = (EmployeeDTO) request.getSession().getAttribute("userInfo");
        //将接收的JSON字符串转换为对象
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = null;
        try {
            employee = mapper.readValue(employeeStr, Employee.class);
        } catch (IOException e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", "传入的JSON字符串有问题");
            return map;
        }
        try {
            //本人修改个人信息
            if (employee.getEmId()==null) {
                employee.setEmId(userInfo.getEmId());
            }
            //调用service层的修改员工方法
            employeeService.update(employee);
        }catch (Exception e){
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        //响应给前端的信息
        map.put("success", true);
        return map;
    }

    @PostMapping("/modifyPwd")
    @ResponseBody
    public Map<String,Object> modifyPwd(EmployeeModifyPwdRequestDTO requestDTO) {
        log.info("进入--modifyPwd--方法,入参String="+requestDTO);
        Map<String,Object> map = new HashMap<>();
        EmployeeDTO userInfo = (EmployeeDTO) request.getSession().getAttribute("userInfo");
        requestDTO.setEmId(userInfo.getEmId());
        if (requestDTO.getOldpwd()==null) {
            map.put("success",false);
            map.put("errMsg","旧密码不得为空");
            return map;
        }
        if (!Objects.equals(requestDTO.getNewpwd(), requestDTO.getConfirmpwd())) {
            map.put("success",false);
            map.put("errMsg","两次密码输入不一致");
            return map;
        }
        map = employeeService.modifyPwd(requestDTO);
        return map;
    }

}
