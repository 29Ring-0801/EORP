package pers.lintao.eorp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.lintao.eorp.dao.DealRecordDao;
import pers.lintao.eorp.dao.ExpenseReportDao;
import pers.lintao.eorp.dto.requestDTO.DealRecordRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ExpenseReportListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ToggleExpenseReportRequestDTO;
import pers.lintao.eorp.dto.responseDTO.ExamineExpenseReportResponseDTO;
import pers.lintao.eorp.dto.responseDTO.ExpenseReportListResponseDTO;
import pers.lintao.eorp.entity.DealRecord;
import pers.lintao.eorp.entity.ExpenseReport;
import pers.lintao.eorp.service.DealRecordService;
import pers.lintao.eorp.service.EmployeeService;
import pers.lintao.eorp.service.ExpenseReportService;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报销单表(ExpenseReport)表服务实现类
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:14
 */
@Service("expenseReportService")
public class ExpenseReportServiceImpl implements ExpenseReportService {

    //经理审批最大额度限制
    public static final Integer ApprovalLimit = 1000;
    //普通员工
    public static final Integer OrdinaryEmployeePosition = 3;
    //经理
    public static final Integer ManagerPosition = 2;
    //总经理
    public static final Integer GeneralManagerPosition = 1;
    //会计
    public static final Integer AccountingPosition = 6;
    //出纳
    public static final Integer CashierPosition = 7;

    @Resource
    private ExpenseReportDao expenseReportDao;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DealRecordDao dealRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param expenseId 主键
     * @return 实例对象
     */
    @Override
    public ExpenseReport queryById(Integer expenseId) {
        return this.expenseReportDao.queryById(expenseId);
    }


    /**
     * 新增数据
     *
     * @param expenseReport 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExpenseReport insert(ExpenseReport expenseReport) {
        this.expenseReportDao.insert(expenseReport);
        return expenseReport;
    }

    /**
     * 修改数据
     *
     * @param expenseReport 实例对象
     * @return 实例对象
     */
    @Override
    public ExpenseReport update(ExpenseReport expenseReport) {
        this.expenseReportDao.update(expenseReport);
        return this.queryById(expenseReport.getExpenseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param expenseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer expenseId) {
        return this.expenseReportDao.deleteById(expenseId) > 0;
    }

    /**
     * 查询报销单列表
     * @return
     */
    @Override
    public Map<String, Object> queryPageList(ExpenseReportListRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            //封装查询条件
            QueryWrapper<ExpenseReportListRequestDTO> wrapper = new QueryWrapper<>();
            if (null != requestDTO.getEmId()){
                wrapper.eq("em_id", requestDTO.getEmId());
            }
            if (null != requestDTO.getStatus()){
                wrapper.eq("status", requestDTO.getStatus());
            }
            Page<ExpenseReportListRequestDTO> page = new Page<>(requestDTO.getCurrent(),requestDTO.getSize());
            IPage<ExpenseReportListResponseDTO> expenseReports = expenseReportDao.queryPageList(page, wrapper);
            map.put("success",true);
            map.put("data",expenseReports);
        }catch (Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
            //如果用到了声明式事务,在catch中捕获异常之后,一定要抛出,否则事务不会回滚
        }
        return map;
    }

    /**
     * 查询待审批报销单列表
     * @return
     */
    @Override
    public Map<String, Object> queryExamineList(Integer nextDealEm) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<ExamineExpenseReportResponseDTO> expenseReports = expenseReportDao.queryExamineList(nextDealEm);
            map.put("success",true);
            map.put("data",expenseReports);
        }catch (Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
            //如果用到了声明式事务,在catch中捕获异常之后,一定要抛出,否则事务不会回滚
        }
        return map;
    }

    /**
     * 修改报销单状态
     * 0 审批单初始状态,1 部门经理审批通过,2 总经理审批通过,3 会计审核通过,4 出纳打款,-1 部门经理打回,-2 总经理打回,-3 财务打回
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Map<String, Object> toggleStatus(ToggleExpenseReportRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        //处理记录对象
        DealRecord dealRecord = new DealRecord();
        dealRecord.setExpensiveId(requestDTO.getExpenseId());
        dealRecord.setEmId(requestDTO.getEmId());
        dealRecord.setDealWay(requestDTO.getExamine());
        dealRecord.setDealTime(new Date());
        //报销单对象
        ExpenseReport expenseReport = new ExpenseReport();
        try {
            //审批通过
            if ("pass".equals(requestDTO.getExamine())) {
                dealRecord.setDealResult("通过");
                int insert = dealRecordDao.insert(dealRecord);
                //部门经理
                if (ManagerPosition.equals(requestDTO.getPositionId())) {
                    //将报销单状态修改为1
                    requestDTO.setStatus(1);
                    expenseReportDao.modifyStatus(requestDTO);
                    ////修改下一审批人,判断报销总金额大小是否需要总经理审批
                    if (requestDTO.getTotalAmount() > ApprovalLimit) {
                        expenseReport.setExpenseId(requestDTO.getExpenseId());
                        expenseReport.setNextDealEm(employeeService.queryAvailableEmployeeByPositionId(GeneralManagerPosition).getEmId());
                        expenseReportDao.update(expenseReport);
                    } else {
                        expenseReport.setExpenseId(requestDTO.getExpenseId());
                        expenseReport.setNextDealEm(employeeService.queryAvailableEmployeeByPositionId(AccountingPosition).getEmId());
                        expenseReportDao.update(expenseReport);
                    }
                }
                //总经理
                if (GeneralManagerPosition.equals(requestDTO.getPositionId())) {
                    //将报销单状态修改为2
                    requestDTO.setStatus(2);
                    expenseReportDao.modifyStatus(requestDTO);
                    //修改下一审批人
                    expenseReport.setExpenseId(requestDTO.getExpenseId());
                    expenseReport.setNextDealEm(employeeService.queryAvailableEmployeeByPositionId(AccountingPosition).getEmId());
                    expenseReportDao.update(expenseReport);
                }
                //会计
                if (AccountingPosition.equals(requestDTO.getPositionId())) {
                    //将报销单状态修改为3
                    requestDTO.setStatus(3);
                    expenseReportDao.modifyStatus(requestDTO);
                    //修改下一审批人为0,表示审批结束
                    expenseReport.setExpenseId(requestDTO.getExpenseId());
                    expenseReport.setNextDealEm(employeeService.queryAvailableEmployeeByPositionId(CashierPosition).getEmId());
                    expenseReportDao.update(expenseReport);
                }
                //出纳
                if (CashierPosition.equals(requestDTO.getPositionId())) {
                    //将报销单状态修改为4
                    requestDTO.setStatus(4);
                    expenseReportDao.modifyStatus(requestDTO);
                    //修改下一审批人为0,表示审批结束
                    expenseReport.setExpenseId(requestDTO.getExpenseId());
                    expenseReport.setNextDealEm(0);
                    expenseReportDao.update(expenseReport);
                }
            }
            //审批打回
            if ("repulse".equals(requestDTO.getExamine())) {
                dealRecord.setDealResult("打回");
                int insert = dealRecordDao.insert(dealRecord);
                //部门经理
                if (ManagerPosition.equals(requestDTO.getPositionId())) {
                    //将报销单状态修改为-1
                    requestDTO.setStatus(-1);
                    expenseReportDao.modifyStatus(requestDTO);
                    //修改下一审批人为0,表示审批结束
                    expenseReport.setExpenseId(requestDTO.getExpenseId());
                    expenseReport.setNextDealEm(0);
                    expenseReportDao.update(expenseReport);
                }
                //总经理
                if (GeneralManagerPosition.equals(requestDTO.getPositionId())) {
                    //将报销单状态修改为-2
                    requestDTO.setStatus(-2);
                    expenseReportDao.modifyStatus(requestDTO);
                    //修改下一审批人为0,表示审批结束
                    expenseReport.setExpenseId(requestDTO.getExpenseId());
                    expenseReport.setNextDealEm(0);
                    expenseReportDao.update(expenseReport);
                }
                //会计
                if (AccountingPosition.equals(requestDTO.getPositionId())) {
                    //将报销单状态修改为-3
                    requestDTO.setStatus(-3);
                    expenseReportDao.modifyStatus(requestDTO);
                    //修改下一审批人为0,表示审批结束
                    expenseReport.setExpenseId(requestDTO.getExpenseId());
                    expenseReport.setNextDealEm(0);
                    expenseReportDao.update(expenseReport);
                }
            }
            map.put("success", true);
        }catch (Exception e){
            //TODO:如果用到了声明式事务,在catch中捕获异常之后,一定要抛出,否则事务不会回滚
            throw e;
        }
        return map;
    }
}
