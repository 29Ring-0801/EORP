package pers.lintao.eorp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pers.lintao.eorp.dto.requestDTO.PositionListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.TogglePositionRequestDTO;
import pers.lintao.eorp.entity.Position;
import pers.lintao.eorp.service.PositionService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lilintao@163.com
 * @time: 2023/4/9 14时39分 周一
 */
@Slf4j
@Controller
@RequestMapping(path = "/position")
public class PositionController extends BaseController{

    @Autowired
    private PositionService positionService;

    @GetMapping("/toList")
    public String toList(){
        log.info("进入--toList--方法...");
        request.getSession().setAttribute("pageName","职位管理");
        return "position-list";
    }

    @GetMapping("/toAddPosition")
    public String toAddPosition(){
        log.info("进入--toAddPosition--方法...");
        request.getSession().setAttribute("pageName","新增职位");
        return "position-add";
    }

    @GetMapping("/goPosition")
    public String goPosition(){
        log.info("进入--goPosition--方法...");
        request.getSession().setAttribute("pageName","职位详情");
        return "position-info";
    }

    @GetMapping("/goPositionEdit")
    public String goPositionEdit(){
        log.info("进入--goPositionEdit--方法...");
        request.getSession().setAttribute("pageName","修改职位信息");
        return "position-edit";
    }

    @PostMapping("/getList")
    @ResponseBody
    public Map<String,Object> getList(@RequestBody PositionListRequestDTO requestDTO){
        log.info("进入--getList--方法,入参PositionListRequestDTO="+requestDTO.toString());
        Map<String,Object> map = null;
        try {
            //调用职位的service层查询数据
            map = positionService.queryPageList(requestDTO);
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/togglePositionStatus")
    @ResponseBody
    public Map<String,Object> togglePositionStatus(@RequestBody TogglePositionRequestDTO requestDTO){
        log.info("进入--togglePositionStatus--方法,入参TogglePositionRequestDTO="+requestDTO.toString());
        Map<String,Object> map = null;
        try {
            //非空校验
            map = positionService.toggleStatus(requestDTO);
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/insertPosition")
    @ResponseBody
    public Map<String,Object> insertPosition(@RequestParam String positionStr){
        log.info("进入--insertPosition--方法,入参String="+positionStr);
        Map<String,Object> map = new HashMap<>();
        //将接收的JSON字符串转换为对象
        ObjectMapper mapper = new ObjectMapper();
        Position position = null;
        try {
            position = mapper.readValue(positionStr, Position.class);
        } catch (Exception e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", "传入的JSON字符串有问题");
            return map;
        }
        try {
            //调用service层的新增职位方法
            Position dep = positionService.insert(position);
            System.err.println("这是新增后返回的带主键的职位对象"+dep);
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

    @PostMapping("/queryPositionById")
    @ResponseBody
    public Map<String,Object> queryPositionById(Integer positionId) {
        log.info("进入--queryPositionById--方法,入参Integer="+positionId.toString());
        Map<String, Object> map = new HashMap<>();

        Position position = null;
        try {
            position = positionService.queryById(positionId);
        } catch (Exception e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        map.put("position", position);
        return map;
    }

    @PostMapping("/editPositionById")
    @ResponseBody
    public Map<String,Object> editPositionById(@RequestParam String positionStr) {
        log.info("进入--editPositionById--方法,入参String="+positionStr);
        Map<String,Object> map = new HashMap<>();
        //将接收的JSON字符串转换为对象
        ObjectMapper mapper = new ObjectMapper();
        Position position = null;
        try {
            position = mapper.readValue(positionStr, Position.class);
        } catch (IOException e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", "传入的JSON字符串有问题");
            return map;
        }
        try {
            //调用service层的新增职位方法
            Position dep = positionService.update(position);
            System.err.println("这是新增后返回的带主键的职位对象"+dep);
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

    @PostMapping("/queryActivePositionList")
    @ResponseBody
    public Map<String,Object> queryActivePositionList(){
        log.info("进入--queryActivePositionList--方法...");
        Map<String,Object> map = null;
        try {
            //调用部门的service层查询数据
            map = positionService.queryActivePositionList();
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
}
