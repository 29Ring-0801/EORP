package pers.lintao.eorp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.lintao.eorp.entity.ExpenseReportDetail;

import java.util.List;

/**
 * 报销单细节表(ExpenseReportDetail)表数据库访问层
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
@Mapper
public interface ExpenseReportDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param expensiveDetailId 主键
     * @return 实例对象
     */
    ExpenseReportDetail queryById(Integer expensiveDetailId);


    /**
     * 统计总行数
     *
     * @param expenseReportDetail 查询条件
     * @return 总行数
     */
    long count(ExpenseReportDetail expenseReportDetail);

    /**
     * 新增数据
     *
     * @param expenseReportDetail 实例对象
     * @return 影响行数
     */
    int insert(ExpenseReportDetail expenseReportDetail);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExpenseReportDetail> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExpenseReportDetail> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExpenseReportDetail> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("detailList") List<ExpenseReportDetail> entities);

    /**
     * 修改数据
     *
     * @param expenseReportDetail 实例对象
     * @return 影响行数
     */
    int update(ExpenseReportDetail expenseReportDetail);

    /**
     * 通过主键删除数据
     *
     * @param expensiveDetailId 主键
     * @return 影响行数
     */
    int deleteById(Integer expensiveDetailId);

    /**
     * 根据报销单id查询报销单详情
     * @param expenseId
     * @return
     */
    List<ExpenseReportDetail> queryByExpenseId(Integer expenseId);

    /**
     * 根据报销单id删除
     * @param expenseId
     */
    void deleteByExpenseId(Integer expenseId);
}

