package pers.lintao.eorp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.lintao.eorp.dto.EmployeeDTO;
import pers.lintao.eorp.dto.requestDTO.*;
import pers.lintao.eorp.entity.ExpenseReport;
import pers.lintao.eorp.entity.ExpenseReportDetail;
import pers.lintao.eorp.service.EmployeeService;
import pers.lintao.eorp.service.ExpenseReportDetailService;
import pers.lintao.eorp.service.ExpenseReportService;
import pers.lintao.eorp.service.impl.ExpenseReportServiceImpl;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报销单表(ExpenseReport)表控制层
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:13
 */
@Slf4j
@Controller
@RequestMapping("expenseReport")
public class ExpenseReportController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private ExpenseReportService expenseReportService;

    @Autowired
    private ExpenseReportDetailService expenseReportDetailService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * 本人报销单列表路由
     * @return
     */
    @GetMapping("/toList")
    public String toList(){
        log.info("进入--toList--方法...");
        request.getSession().setAttribute("pageName","报销单列表");
        return "expense-report-list";
    }

    /**
     * 待本人审批报销单列表
     * @return
     */
    @GetMapping("/toExamineList")
    public String toExamineList(){
        log.info("进入--toExamineList--方法...");
        request.getSession().setAttribute("pageName","审批报销单列表");
        return "examine-expense-report-list";
    }

    /**
     * 查看报销单详情路由
     * @return
     */
    @GetMapping("/goExpenseReport")
    public String goExpenseReport(){
        log.info("进入--goExpenseReport--方法...");
        request.getSession().setAttribute("pageName","报销单详情");
        return "expense-report-info";
    }

    @GetMapping("/goExamineExpenseReport")
    public String goExamineExpenseReport(){
        log.info("进入--goExamineExpenseReport--方法...");
        request.getSession().setAttribute("pageName","审批报销单详情");
        return "examine-expense-report-info";
    }

    @GetMapping("/goExpenseReportEdit")
    public String goExpenseReportEdit(){
        log.info("进入--goExpenseReportEdit--方法...");
        request.getSession().setAttribute("pageName","修改报销单");
        return "expense-report-edit";
    }

    /**
     * 获取本人报销单
     * @param requestDTO
     * @return
     */
    @PostMapping("/getList")
    @ResponseBody
    public Map<String,Object> getList(@RequestBody ExpenseReportListRequestDTO requestDTO){
        log.info("进入--getList--方法,入参ExpenseReportListRequestDTO="+requestDTO.toString());
        Map<String,Object> map = null;
        try {
            //调用部门的service层查询数据
            EmployeeDTO userInfo = (EmployeeDTO)request.getSession().getAttribute("userInfo");
            requestDTO.setEmId(userInfo.getEmId());
            map = expenseReportService.queryPageList(requestDTO);
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取待本人审批审批单列表
     * @param requestDTO
     * @return
     */
    @PostMapping("/getExamineList")
    @ResponseBody
    public Map<String,Object> getExamineList(@RequestBody ExpenseReportListRequestDTO requestDTO){
        log.info("进入--getExamineList--方法,入参ExpenseReportListRequestDTO="+requestDTO.toString());
        Map<String,Object> map = null;
        try {
            //获取登录用户信息
            EmployeeDTO userInfo = (EmployeeDTO)request.getSession().getAttribute("userInfo");
            //调用报销单的service层查询数据
            map = expenseReportService.queryExamineList(userInfo.getEmId());
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("/toAddExpenseReport")
    public String toAddExpenseReport(){
        log.info("进入--toAddExpenseReport--方法...");
        request.getSession().setAttribute("pageName", "新增报销单");
        return "expense-report-add";
    }

    @ResponseBody
    @PostMapping("/insertExpenseReport")
    public Map<String,Object> insertExpenseReport(@RequestParam String detailStr,MultipartFile expenseVoucher){
        log.info("进入--insertExpenseReport--方法,入参String={},MultipartFile={}",detailStr,expenseVoucher);
        Map<String,Object> map = new HashMap<>();
        //将json字符串反序列化成java对象
        ObjectMapper mapper = new ObjectMapper();
        ExpenseReportRequestDTO requestDTO = null;
        try {
            requestDTO = mapper.readValue(detailStr, ExpenseReportRequestDTO.class);
        } catch (IOException e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", "传入的JSON字符串有问题");
            return map;
        }
        //操作DTO
        if (requestDTO != null && !"".equals(requestDTO)) {
            if (requestDTO.getExpenseReport().getCause() != null && requestDTO.getExpenseReport().getTotalAmount() != null) {
                //从session中获取员工id
                EmployeeDTO userInfo = (EmployeeDTO) request.getSession().getAttribute("userInfo");
                requestDTO.getExpenseReport().setEmId(userInfo.getEmId());
                //报销单创建时间
                Date createTime = new Date();
                requestDTO.getExpenseReport().setCreateTime(createTime);
                if (ExpenseReportServiceImpl.OrdinaryEmployeePosition.equals(userInfo.getPositionId())) {
                    //普通员工提交的,报销单下一审批人id(部门经理)
                    EmployeeDTO nextDealEm = employeeService.getEmployLeadByDepId(userInfo.getDepId());
                    requestDTO.getExpenseReport().setNextDealEm(nextDealEm.getEmId());
                } else if (ExpenseReportServiceImpl.ManagerPosition.equals(userInfo.getPositionId())) {
                    //部门经理提交的,下一审批人id为总经理
                    EmployeeDTO nextDealEm = employeeService.queryAvailableEmployeeByPositionId(ExpenseReportServiceImpl.GeneralManagerPosition);
                    requestDTO.getExpenseReport().setNextDealEm(nextDealEm.getEmId());
                } else if (ExpenseReportServiceImpl.GeneralManagerPosition.equals(userInfo.getPositionId())) {
                    //总经理提交的,下一审批人id为会计
                    EmployeeDTO nextDealEm = employeeService.queryAvailableEmployeeByPositionId(ExpenseReportServiceImpl.AccountingPosition);
                    requestDTO.getExpenseReport().setNextDealEm(nextDealEm.getEmId());
                }
                //报销单状态
                requestDTO.getExpenseReport().setStatus(0);

                //操作报销单凭证
                if (expenseVoucher != null) {
                    //转存凭证文件,并修改文件名,将文件名作为唯一标识存入报销表中
                    //1.获取上传文件的原名
                    String uploadFileName = expenseVoucher.getOriginalFilename();
                    //2.获取文件后缀名
                    String suffix = "";
                    if (uploadFileName.contains(".")) {
                        suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."));
                    }
                    //3.自定义文件名,用时间戳和员工id
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                    String expenseVoucherString = dateFormat.format(createTime);
                    String fileName = expenseVoucherString + "_" + userInfo.getEmId() + suffix;
                    //将报销单凭证的文件名作为报销凭证的唯一表示
                    requestDTO.getExpenseReport().setExpenseVoucher(fileName);
                    //4.文件存放路径
                    String filePath = "D:\\IDEA_2021.2\\WorkingSpace\\EORP\\src\\main\\resources\\static\\upload-voucher";
                    File file = new File(filePath + File.separator + fileName);
                    try {
                        expenseVoucher.transferTo(file);
                        System.err.println(file.getAbsolutePath());
                    } catch (IOException e) {
                        map.put("success", false);
                        map.put("errMsg", "报销凭证上传失败" + e.getMessage());
                        return map;
                    }
                }
                //开始新增数据
                ExpenseReport insert = null;
                try {
                    //获取新增返回后带主键的报销单信息
                    insert = expenseReportService.insert(requestDTO.getExpenseReport());
                    System.out.println(insert);
                    //遍历报销单详情,并给上所属报销单编号
                    for (ExpenseReportDetail expenseReportDetail : requestDTO.getDetailList()) {
                        expenseReportDetail.setExpensiveId(insert.getExpenseId());
                    }
                    //向报销单详情表中批量插入数据
                    expenseReportDetailService.insertBatch(requestDTO.getDetailList());
                    map.put("success", true);
                } catch (Exception e) {
                    map.put("success", false);
                    map.put("errMsg", e.getMessage());
                }
            } else {
                map.put("success", false);
                map.put("errMsg", "报销原因或报销金额不能为空");
            }
        }
        return map;
    }

    @PostMapping("/queryExpenseReportById")
    @ResponseBody
    public Map<String,Object> queryExpenseReportById(Integer expenseId) {
        log.info("进入--queryExpenseReportById--方法,入参Integer="+expenseId.toString());
        Map<String, Object> map = new HashMap<>();

        ExpenseReport expenseReport = null;
        List<ExpenseReportDetail> reportDetails = null;
        try {
            expenseReport = expenseReportService.queryById(expenseId);
            reportDetails = expenseReportDetailService.queryByExpenseId(expenseId);
        } catch (Exception e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        map.put("expenseReport", expenseReport);
        map.put("reportDetails", reportDetails);
        return map;
    }

    @PostMapping("/editExpenseReportById")
    @ResponseBody
    public Map<String,Object> editExpenseReportById(@RequestParam String detailStr,MultipartFile expenseVoucher) {
        log.info("进入--editExpenseReportById--方法,入参String="+detailStr+",MultipartFile可为空");
        Map<String,Object> map = new HashMap<>();
        //将json字符串反序列化成java对象
        ObjectMapper mapper = new ObjectMapper();
        ExpenseReportRequestDTO requestDTO = null;
        try {
            requestDTO = mapper.readValue(detailStr, ExpenseReportRequestDTO.class);
        } catch (IOException e) {
            //响应给前端的信息
            map.put("success", false);
            map.put("errMsg", "传入的JSON字符串有问题");
            return map;
        }
        //操作DTO
        if (requestDTO != null && !"".equals(requestDTO)) {
            Integer expenseId = requestDTO.getExpenseReport().getExpenseId();
            //获取数据库更新前的数据
            ExpenseReport expenseReport = expenseReportService.queryById(expenseId);
            requestDTO.getExpenseReport().setCreateTime(expenseReport.getCreateTime());
            requestDTO.getExpenseReport().setEmId(expenseReport.getEmId());
            requestDTO.getExpenseReport().setNextDealEm(expenseReport.getNextDealEm());
            requestDTO.getExpenseReport().setStatus(expenseReport.getStatus());
            //操作报销单凭证
            if (expenseVoucher!=null){
                //修改前数据库报销凭证文件名
                String fileName = expenseReport.getExpenseVoucher();
                //获取上传文件的后缀
                String originalFilename = expenseVoucher.getOriginalFilename();
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") );
                //保证文件名不变,只改后缀
                String name = fileName.substring(0, fileName.lastIndexOf("."));
                fileName = name+suffix;
                //修改数据库中报销凭证的文件名
                requestDTO.getExpenseReport().setExpenseVoucher(fileName);
                String filePath = "D:\\IDEA_2021.2\\WorkingSpace\\SSM-0420\\src\\main\\webapp\\WEB-INF\\upload-voucher";
                File file = new File(filePath+File.separator+fileName);
                try {
                    expenseVoucher.transferTo(file);
                    System.err.println(file.getAbsolutePath());
                } catch (IOException e) {
                    map.put("success", false);
                    map.put("errMsg", "报销凭证上传失败"+e.getMessage());
                    return map;
                }
            }
            //开始新增数据
            ExpenseReport insert = null;
            try{
                //获取新增返回后带更新后的报销单信息
                insert = expenseReportService.update(requestDTO.getExpenseReport());
                //遍历报销单详情,并给上所属报销单编号
                for (ExpenseReportDetail expenseReportDetail : requestDTO.getDetailList()) {
                    expenseReportDetail.setExpensiveId(requestDTO.getExpenseReport().getExpenseId());
                }
                expenseReportDetailService.update(requestDTO);
                map.put("success", true);
            }catch (Exception e){
                map.put("success", false);
                map.put("errMsg", e.getMessage());
            }
        }
        return map;
    }

    /**
     * 修改报销单状态
     * @param requestDTO
     * @return
     */
    @PostMapping("/toggleExpenseReportStatus")
    @ResponseBody
    public Map<String,Object> toggleExpenseReportStatus(ToggleExpenseReportRequestDTO requestDTO){
        log.info("进入--toggleExpenseReportStatus--方法,入参ToggleDepartmentRequestDTO="+requestDTO.toString());
        Map<String,Object> map = null;
        EmployeeDTO userInfo = (EmployeeDTO)request.getSession().getAttribute("userInfo");
        requestDTO.setEmId(userInfo.getEmId());
        requestDTO.setPositionId(userInfo.getPositionId());
        try {
            //非空校验
            map = expenseReportService.toggleStatus(requestDTO);
        }catch(Exception e){
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
}

