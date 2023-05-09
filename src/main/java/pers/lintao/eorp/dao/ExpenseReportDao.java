package pers.lintao.eorp.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.lintao.eorp.dto.requestDTO.ExpenseReportListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ToggleExpenseReportRequestDTO;
import pers.lintao.eorp.dto.responseDTO.ExamineExpenseReportResponseDTO;
import pers.lintao.eorp.dto.responseDTO.ExpenseReportListResponseDTO;
import pers.lintao.eorp.entity.ExpenseReport;
import java.util.List;

/**
 * 报销单表(ExpenseReport)表数据库访问层
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:14
 */
@Mapper
public interface ExpenseReportDao {

    /**
     * 通过ID查询单条数据
     *
     * @param expenseId 主键
     * @return 实例对象
     */
    ExpenseReport queryById(Integer expenseId);


    /**
     * 统计总行数
     *
     * @param expenseReport 查询条件
     * @return 总行数
     */
    long count(ExpenseReport expenseReport);

    /**
     * 新增数据
     *
     * @param expenseReport 实例对象
     * @return 影响行数
     */
    int insert(ExpenseReport expenseReport);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExpenseReport> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ExpenseReport> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ExpenseReport> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ExpenseReport> entities);

    /**
     * 修改数据
     *
     * @param expenseReport 实例对象
     * @return 影响行数
     */
    int update(ExpenseReport expenseReport);

    /**
     * 通过主键删除数据
     *
     * @param expenseId 主键
     * @return 影响行数
     */
    int deleteById(Integer expenseId);

    /**
     * 获取报销单列表
     * @param page
     * @param wrapper
     * @return
     */
    IPage<ExpenseReportListResponseDTO> queryPageList(Page<ExpenseReportListRequestDTO> page, @Param("ew") QueryWrapper<ExpenseReportListRequestDTO> wrapper);

    /**
     * 获取待审批列表
     * @param page
     * @param wrapper
     * @return
     */
    List<ExamineExpenseReportResponseDTO> queryExamineList(Integer nextDealEm);

    /**
     * 根据报销单Id和status嘛修改报销单状态
     * @param requestDTO
     */
    void modifyStatus(ToggleExpenseReportRequestDTO requestDTO);
}

