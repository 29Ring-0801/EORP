package pers.lintao.eorp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.lintao.eorp.entity.DealRecord;
import java.util.List;

/**
 * 操作记录表(DealRecord)表数据库访问层
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
@Mapper
public interface DealRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param recordId 主键
     * @return 实例对象
     */
    DealRecord queryById(Integer recordId);

    /**
     * 统计总行数
     *
     * @param dealRecord 查询条件
     * @return 总行数
     */
    long count(DealRecord dealRecord);

    /**
     * 新增数据
     *
     * @param dealRecord 实例对象
     * @return 影响行数
     */
    int insert(DealRecord dealRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DealRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DealRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DealRecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DealRecord> entities);

    /**
     * 修改数据
     *
     * @param dealRecord 实例对象
     * @return 影响行数
     */
    int update(DealRecord dealRecord);

    /**
     * 通过主键删除数据
     *
     * @param recordId 主键
     * @return 影响行数
     */
    int deleteById(Integer recordId);

}

