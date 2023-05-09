package pers.lintao.eorp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pers.lintao.eorp.dao.DepartmentDao;
import pers.lintao.eorp.dao.EmployeeDao;
import pers.lintao.eorp.dao.PositionDao;
import pers.lintao.eorp.dto.EmployeeDTO;
import pers.lintao.eorp.dto.requestDTO.EmployeeListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.EmployeeModifyPwdRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ToggleEmployeeRequestDTO;
import pers.lintao.eorp.dto.responseDTO.ActiveDepartmentListResponseDTO;
import pers.lintao.eorp.dto.responseDTO.ActivePositionListResponseDTO;
import pers.lintao.eorp.dto.responseDTO.EmployeeResponseDTO;
import pers.lintao.eorp.entity.Employee;
import pers.lintao.eorp.service.EmployeeService;
import pers.lintao.eorp.utils.MD5Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 员工表(Employee)表服务实现类
 *
 * @author lilintao@163.com
 * @since 2023-04-24 10:15:44
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private PositionDao positionDao;

    @Override
    public EmployeeDTO getEmInfoByUserNamePassword(String username, String password) {
        return employeeDao.getEmInfoByUserNamePassword(username, MD5Util.getMd5(password));
    }

    @Override
    public Map<String, Object> queryPageList(EmployeeListRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            //封装查询条件
            QueryWrapper<EmployeeListRequestDTO> wrapper = new QueryWrapper<>();
            //MyBatis-Plus使用wrapper封装条件,相当于MyBatis的动态SQL
            //判断是否!=null或者!=""
            wrapper.like(!StringUtils.isEmpty(requestDTO.getName()),"name",requestDTO.getName())
                    .eq(Objects.nonNull(requestDTO.getDepId()),"dep_id",requestDTO.getDepId())
                    .eq(Objects.nonNull(requestDTO.getPositionId()),"position_id",requestDTO.getPositionId())
                    .eq(Objects.nonNull(requestDTO.getStatus()),"status",requestDTO.getStatus());
            Page<EmployeeListRequestDTO> page = new Page<>(requestDTO.getCurrent(),requestDTO.getSize());
            IPage<EmployeeDTO> departments = employeeDao.queryPageList(page, wrapper);
            map.put("success",true);
            map.put("data",departments);
        }catch (Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
            //如果用到了声明式事务,在catch中捕获异常之后,一定要抛出,否则事务不会回滚
        }
        return map;
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @Override
    public void insert(Employee employee) {
        employee.setPassword(MD5Util.getMd5("123456"));
        int insert = employeeDao.insert(employee);
        System.out.println("***********EmployeeServiceImpl类中的insert()方法返回值"+insert+"****************");
    }

    /**
     * 对员工状态进行更换
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Map<String, Object> toggleStatus(ToggleEmployeeRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        Integer status = requestDTO.getStatus();
        Integer emId = requestDTO.getEmId();
        try {
            if (1 == status){
                //将状态改为禁用:0
                employeeDao.updateFailureStatusById(emId);
                //测试事务回滚
                //throw new Exception("状态修改异常");
            }else {
                //将状态改为启用:1
                employeeDao.updateSuccessStatusById(emId);
            }
            map.put("success", true);
        }catch (Exception e){
            //TODO:如果用到了声明式事务,在catch中捕获异常之后,一定要抛出,否则事务不会回滚
            throw e;
        }
        return map;
    }

    /**
     * 根据员工id查询员工信息
     * @param employeeId
     * @return
     */
    @Override
    public EmployeeResponseDTO queryById(Integer employeeId) throws Exception {
        try {
            Employee employee = employeeDao.selectById(employeeId);
            if (Objects.isNull(employee)){
                throw new Exception("没有查询到员工信息");
            }
            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();

            List<ActivePositionListResponseDTO> activePositionList = positionDao.queryActivePositionList();
            List<ActiveDepartmentListResponseDTO> activeDepartmentList = departmentDao.queryActiveDepartmentList();
            employeeResponseDTO.setEmployee(employee);
            employeeResponseDTO.setActivePositionList(activePositionList);
            employeeResponseDTO.setActiveDepartmentList(activeDepartmentList);
            return employeeResponseDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @Override
    public void update(Employee employee) {
        int i = employeeDao.updateById(employee);
    }

    /**
     * 根据部门id获取部门经理
     * @param depId
     * @return
     */
    @Override
    public EmployeeDTO getEmployLeadByDepId(Integer depId) {
        return employeeDao.getEmployLeadByDepId(depId);
    }

    /**
     * 根据职位id查询在职的员工
     * @param positionId
     * @return
     */
    @Override
    public EmployeeDTO queryAvailableEmployeeByPositionId(Integer positionId) {
        return employeeDao.queryAvailableEmployeeByPositionId(positionId);
    }

    /**
     * 修改密码
     * @param requestDTO
     */
    @Override
    @Transactional
    public Map<String, Object> modifyPwd(EmployeeModifyPwdRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        requestDTO.setOldpwd(MD5Util.getMd5(requestDTO.getOldpwd()));
        requestDTO.setNewpwd(MD5Util.getMd5(requestDTO.getNewpwd()));
        requestDTO.setConfirmpwd(MD5Util.getMd5(requestDTO.getConfirmpwd()));
        try {
            int i = employeeDao.modifyPwd(requestDTO);
            System.out.println("****************更新密码: "+i);
            if (i > 0) {
                map.put("success", true);
                map.put("errMsg", "密码修改成功");
            } else {
                map.put("success", false);
                map.put("errMsg", "密码修改失败");
            }
            return map;
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", "密码修改失败");
            throw e;
        }
    }

}

