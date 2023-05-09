package pers.lintao.eorp.service;

import pers.lintao.eorp.dto.requestDTO.ExpenseReportRequestDTO;
import pers.lintao.eorp.entity.ExpenseReportDetail;

import java.util.List;
import java.util.Map;

/**
 * 报销单细节表(ExpenseReportDetail)表服务接口
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
public interface ExpenseReportDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param expensiveDetailId 主键
     * @return 实例对象
     */
    ExpenseReportDetail queryById(Integer expensiveDetailId);

    /**
     * 新增数据
     *
     * @param expenseReportDetail 实例对象
     * @return 实例对象
     */
    ExpenseReportDetail insert(ExpenseReportDetail expenseReportDetail);

    /**
     * 修改数据
     *
     * @param requestDTO 实例对象
     * @return 实例对象
     */
    Map<String,Object> update(ExpenseReportRequestDTO requestDTO);

    /**
     * 通过主键删除数据
     *
     * @param expensiveDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer expensiveDetailId);

    /**
     * 批量新增
     * @param detailList
     */
    void insertBatch(List<ExpenseReportDetail> detailList);

    /**
     * 根据报销单Id查询报销详情
     * @param expenseId
     */
    List<ExpenseReportDetail> queryByExpenseId(Integer expenseId);

    /**
     * 根据报销单id删除
     * @param expenseId
     */
    void deleteByExpenseId(Integer expenseId);
}
