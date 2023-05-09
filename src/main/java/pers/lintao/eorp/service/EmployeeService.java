package pers.lintao.eorp.service;

import pers.lintao.eorp.dto.EmployeeDTO;
import pers.lintao.eorp.dto.requestDTO.EmployeeListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.EmployeeModifyPwdRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ToggleEmployeeRequestDTO;
import pers.lintao.eorp.dto.responseDTO.EmployeeResponseDTO;
import pers.lintao.eorp.entity.Employee;

import java.util.Map;

/**
 * 员工表(Employee)表服务接口
 *
 * @author lilintao@163.com
 * @since 2023-04-24 10:15:44
 */
public interface EmployeeService{

    /**
     * 通过用户名和密码联合查询员工详细详细
     * @param username
     * @param password
     * @return
     */
    EmployeeDTO getEmInfoByUserNamePassword(String username, String password);

    /**
     * 查询员工列表
     * @param requestDTO
     * @return
     */
    Map<String, Object> queryPageList(EmployeeListRequestDTO requestDTO);

    /**
     * 新增员工
     * @param employee
     * @return
     */
    void insert(Employee employee);

    /**
     * 对员工状态进行更换
     * @param requestDTO
     * @return
     */
    Map<String, Object> toggleStatus(ToggleEmployeeRequestDTO requestDTO);

    /**
     * 根据员工id查询员工信息
     * @param employeeId
     * @return
     */
    EmployeeResponseDTO queryById(Integer employeeId) throws Exception;

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    void update(Employee employee);

    /**
     * 根据部门id获取部门经理
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

    /**
     * 修改密码
     * @param requestDTO
     */
    Map<String, Object> modifyPwd(EmployeeModifyPwdRequestDTO requestDTO);
}

