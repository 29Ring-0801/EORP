package pers.lintao.eorp.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.lintao.eorp.dto.EmployeeDTO;
import pers.lintao.eorp.dto.requestDTO.EmployeeListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.EmployeeModifyPwdRequestDTO;
import pers.lintao.eorp.entity.Employee;

/**
 * 员工表(Employee)表数据库访问层
 *
 * @author 29Ring
 * @since 2023-04-24 10:15:43
 */
@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {

    /**
     * 根据员工id查询
     * @param emId
     * @return
     */
    Employee queryById(Integer emId);

    /**
     * 根据用户名和密码查找员工dto
     * @param username
     * @param password
     * @return
     */
    EmployeeDTO getEmInfoByUserNamePassword(@Param("loginName") String username, @Param("password") String password);

    /**
     * 条件+分页查询员工列表
     * @param page
     * @param wrapper
     * @return
     */
    IPage<EmployeeDTO> queryPageList(Page<EmployeeListRequestDTO> page, @Param("ew") QueryWrapper<EmployeeListRequestDTO> wrapper);

    /**
     * 将员工状态改为禁用
     * @param emId
     */
    void updateFailureStatusById(Integer emId);

    /**
     * 将员工状态改为启用
     * @param emId
     */
    void updateSuccessStatusById(Integer emId);

    /**
     * 更新员工信息
     * @param employee
     * @return
     */
    @Override
    int updateById(Employee employee);

    /**
     * 根据部门id获取部门经理信息
     * @param depId
     * @return
     */
    EmployeeDTO getEmployLeadByDepId(Integer depId);

    /**
     * 根据职位id查询在职的员工
     * @param positionId
     * @return
     */
    EmployeeDTO queryAvailableEmployeeByPositionId(Integer positionId);

    Integer queryByIdAndPwd(EmployeeModifyPwdRequestDTO requestDTO);

    int modifyPwd(EmployeeModifyPwdRequestDTO requestDTO);
}

