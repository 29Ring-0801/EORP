package pers.lintao.eorp.service;


import pers.lintao.eorp.dto.requestDTO.DepartmentListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ToggleDepartmentRequestDTO;
import pers.lintao.eorp.entity.Department;
import pers.lintao.eorp.utils.MyException;

import java.util.Map;


/**
 * 部门表(Department)表服务接口
 *
 * @author lilintao@163.com
 * @since 2023-04-24 12:21:27
 */
public interface DepartmentService {

    /**
     * 查询部门列表
     * @return
     */
    Map<String, Object> queryPageList(DepartmentListRequestDTO requestDTO) throws MyException;

    /**
     * 对部门状态进行更换
     * @param requestDTO
     * @return
     */
    Map<String, Object> toggleStatus(ToggleDepartmentRequestDTO requestDTO) throws Exception;

    /**
     * 新增部门
     * @param department
     * @return
     */
    Department insert(Department department);

    /**
     * 根据部门id查询部门信息
     * @param departmentId
     * @return
     */
    Department queryById(Integer departmentId);

    /**
     * 根据部门id修改部门信息
     * @param department
     * @return
     */
    Department update(Department department);

    /**
     * 获取状态为 1 的部门列表
     * @return
     */
    Map<String, Object> queryActiveDepartmentList();
}
