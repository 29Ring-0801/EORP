package pers.lintao.eorp.service;

import pers.lintao.eorp.dto.requestDTO.DealRecordRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ExpenseReportListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ToggleExpenseReportRequestDTO;
import pers.lintao.eorp.entity.ExpenseReport;
import java.util.Map;

/**
 * 报销单表(ExpenseReport)表服务接口
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:14
 */
public interface ExpenseReportService {

    /**
     * 通过ID查询单条数据
     *
     * @param expenseId 主键
     * @return 实例对象
     */
    ExpenseReport queryById(Integer expenseId);


    /**
     * 新增数据
     *
     * @param expenseReport 实例对象
     * @return 实例对象
     */
    ExpenseReport insert(ExpenseReport expenseReport);

    /**
     * 修改数据
     *
     * @param expenseReport 实例对象
     * @return 实例对象
     */
    ExpenseReport update(ExpenseReport expenseReport);

    /**
     * 通过主键删除数据
     *
     * @param expenseId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer expenseId);

    /**
     * 查询报销单列表
     * @return
     */
    Map<String, Object> queryPageList(ExpenseReportListRequestDTO requestDTO);

    /**
     * 查询待审批报销单列表
     * @param nextDealEm
     * @return
     */
    Map<String, Object> queryExamineList(Integer nextDealEm);

    /**
     * 修改报销单状态
     * @param requestDTO
     * @return
     */
    Map<String, Object> toggleStatus(ToggleExpenseReportRequestDTO requestDTO);
}
