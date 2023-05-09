package pers.lintao.eorp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.lintao.eorp.dto.requestDTO.DepartmentListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ToggleDepartmentRequestDTO;
import pers.lintao.eorp.entity.Department;
import pers.lintao.eorp.service.DepartmentService;
import pers.lintao.eorp.utils.MyException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 部门模块
 * @author: lilintao@163.com
 * @time: 2023/4/26 12时04分 周二
 */
@Slf4j
@Controller
@RequestMapping(path = "/department")
public class DepartmentController extends BaseController{

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/toList")
    public String toList(){
        log.info("进入--toList--方法...");
        request.getSession().setAttribute("pageName","部门管理");
        return "department-list";
    }

    @GetMapping("/toAddDepartment")
    public String toAddDepartment(){
        log.info("进入--toAddDepartment--方法...");
        request.getSession().setAttribute("pageName","新增部门");
        return "department-add";
    }

    @GetMapping("/goDepartment")
    public String goDepartment(){
        log.info("进入--goDepartment--方法...");
        request.getSession().setAttribute("pageName","部门详情");
        return "department-info";
    }

    @GetMapping("/goDepartmentEdit")
    public String goDepartmentEdit(){
        log.info("进入--goDepartmentEdit--方法...");
        request.getSession().setAttribute("pageName","修改部门信息");
        return "department-edit";
    }


    @PostMapping("/getList")
    @ResponseBody
    public Map<String,Object> getList(@RequestBody DepartmentListRequestDTO requestDTO) throws MyException {
        log.info("进入--getList--方法,入参DepartmentListRequestDTO="+requestDTO.toString());
        Map<String,Object> map = null;
        try {
            //调用部门的service层查询数据
            map = departmentService.queryPageList(requestDTO);
        }catch(MyException e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            throw e;
        }
        return map;
    }

    @PostMapping("/toggleDepartmentStatus")
    @ResponseBody
    public Map<String,Object> toggleDepartmentStatus(@RequestBody ToggleDepartmentRequestDTO requestDTO){
        log.info("进入--toggleDepartmentStatus--方法,入参ToggleDepartmentRequestDTO="+requestDTO.toString());
        Map<String,Object> map = null;
        try {
            //非空校验
            map = departmentService.toggleStatus(requestDTO);
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/insertDepartment")
    @ResponseBody
    public Map<String,Object> insertDepartment(@RequestParam String departmentStr){
        log.info("进入--insertDepartment--方法,入参String="+departmentStr);
        Map<String,Object> map = new HashMap<>();
        //将接收的JSON字符串转换为对象
        ObjectMapper mapper = new ObjectMapper();
        Department department = null;
        try {
            department = mapper.readValue(departmentStr, Department.class);
        } catch (Exception e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", "传入的JSON字符串有问题");
            return map;
        }
        try {
            //调用service层的新增部门方法
            Department dep = departmentService.insert(department);
            System.err.println("这是新增后返回的带主键的部门对象"+dep);
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

    @PostMapping("/queryDepartmentById")
    @ResponseBody
    public Map<String,Object> queryDepartmentById(Integer departmentId) {
        log.info("进入--queryDepartmentById--方法,入参Integer="+departmentId.toString());
        Map<String, Object> map = new HashMap<>();
        Department department = null;
        try {
            department = departmentService.queryById(departmentId);
        } catch (Exception e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        map.put("department", department);
        return map;
    }

    @PostMapping("/editDepartmentById")
    @ResponseBody
    public Map<String,Object> editDepartmentById(@RequestParam String departmentStr) {
        log.info("进入--editDepartmentById--方法,入参String="+departmentStr);
        Map<String,Object> map = new HashMap<>();
        //将接收的JSON字符串转换为对象
        ObjectMapper mapper = new ObjectMapper();
        Department department = null;
        try {
            department = mapper.readValue(departmentStr, Department.class);
        } catch (IOException e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", "传入的JSON字符串有问题");
            return map;
        }
        try {
            //调用service层的新增部门方法
            Department dep = departmentService.update(department);
            System.err.println("这是新增后返回的带主键的部门对象"+dep);
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

    @PostMapping("/queryActiveDepartmentList")
    @ResponseBody
    public Map<String,Object> queryActiveDepartmentList(){
        log.info("进入--queryActiveDepartmentList--方法...");
        Map<String,Object> map = null;
        try {
            //调用部门的service层查询数据
            map = departmentService.queryActiveDepartmentList();
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
}
