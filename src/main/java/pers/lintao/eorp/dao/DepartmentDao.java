package pers.lintao.eorp.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.lintao.eorp.dto.requestDTO.DepartmentListRequestDTO;
import pers.lintao.eorp.dto.responseDTO.ActiveDepartmentListResponseDTO;
import pers.lintao.eorp.dto.responseDTO.DepartmentListResponseDTO;
import pers.lintao.eorp.entity.Department;

import java.util.List;

/**
 * 部门表(Department)表数据库访问层
 *
 * @author 29Ring
 * @since 2023-04-24 13:10:09
 */
@Mapper
public interface DepartmentDao{

    /**
     * 通过ID查询单条数据
     *
     * @param depId 主键
     * @return 实例对象
     */
    Department queryById(Integer depId);


    /**
     * 统计总行数
     *
     * @param department 查询条件
     * @return 总行数
     */
    long count(Department department);

    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 影响行数
     */
    int insert(Department department);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Department> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Department> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Department> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Department> entities);

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 影响行数
     */
    int update(Department department);

    /**
     * 通过主键删除数据
     *
     * @param depId 主键
     * @return 影响行数
     */
    int deleteById(Integer depId);

    /**
     * 分页+筛选查询部门列表
     * @param requestDTO
     * @return
     */
    //List<DepartmentListResponseDTO> queryPageList(DepartmentListRequestDTO requestDTO);

    /**
     * mybatis-plus分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<DepartmentListResponseDTO> queryPageList(Page<DepartmentListRequestDTO> page, @Param("ew") Wrapper<DepartmentListRequestDTO> queryWrapper);

    /**
     * 将部门状态改为禁用
     * @param depId
     */
    void updateFailureStatusById(Integer depId);

    /**
     * 将部门状态改为启用
     * @param depId
     */
    void updateSuccessStatusById(Integer depId);

    /**
     * 查询部门状态为1 的部门列表
     * @return
     */
    List<ActiveDepartmentListResponseDTO> queryActiveDepartmentList();
}

