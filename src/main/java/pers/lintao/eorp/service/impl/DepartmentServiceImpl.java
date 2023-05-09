package pers.lintao.eorp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.lintao.eorp.dao.DepartmentDao;
import pers.lintao.eorp.dto.requestDTO.DepartmentListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.ToggleDepartmentRequestDTO;
import pers.lintao.eorp.dto.responseDTO.ActiveDepartmentListResponseDTO;
import pers.lintao.eorp.dto.responseDTO.DepartmentListResponseDTO;
import pers.lintao.eorp.entity.Department;
import pers.lintao.eorp.service.DepartmentService;
import pers.lintao.eorp.utils.MyException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门表(Department)表服务实现类
 *
 * @author lilintao@163.com
 * @since 2023-04-24 12:21:27
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 因为这里只是单纯的查询,所以不涉及事务
     * @return
     */
    @Override
    public Map<String, Object> queryPageList(DepartmentListRequestDTO requestDTO) throws MyException {
        Map<String, Object> map = new HashMap<>();
        try {
            //List<DepartmentListResponseDTO> departments = departmentDao.queryPageList(requestDTO);
            //封装查询条件
            QueryWrapper<DepartmentListRequestDTO> wrapper = new QueryWrapper<>();
            if (null != requestDTO.getName()){
                wrapper.like("name", requestDTO.getName());
            }
            if (null != requestDTO.getAddress()){
                wrapper.like("address", requestDTO.getAddress());
            }
            if (null != requestDTO.getStatus()){
                wrapper.eq("status", requestDTO.getStatus());
            }
            Page<DepartmentListRequestDTO> page = new Page<>(requestDTO.getCurrent(),requestDTO.getSize());
            IPage<DepartmentListResponseDTO> departments = departmentDao.queryPageList(page, wrapper);
            map.put("success",true);
            map.put("data",departments);
        }catch (Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
            //如果用到了声明式事务,在catch中捕获异常之后,一定要抛出,否则事务不会回滚
            throw new MyException("分页查询异常"+e.getMessage());
        }
        return map;
    }

    /**
     * 对部门状态进行更换
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Map<String, Object> toggleStatus(ToggleDepartmentRequestDTO requestDTO) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Integer status = requestDTO.getStatus();
        Integer depId = requestDTO.getDepId();
        try {
            if (1 == status){
                //将状态改为禁用:0
                departmentDao.updateFailureStatusById(depId);
                //测试事务回滚
                //throw new Exception("状态修改异常");
            }else {
                //将状态改为启用:1
                departmentDao.updateSuccessStatusById(depId);
            }
            map.put("success", true);
        }catch (Exception e){
            //TODO:如果用到了声明式事务,在catch中捕获异常之后,一定要抛出,否则事务不会回滚
            throw e;
        }
        return map;
    }

    /**
     * 新增部门
     * @param department
     * @return
     */
    @Override
    public Department insert(Department department) {
        departmentDao.insert(department);
        return department;
    }

    /**
     * 根据部门id查询部门信息
     * @param departmentId
     * @return
     */
    @Override
    public Department queryById(Integer departmentId) {
        Department department = departmentDao.queryById(departmentId);
        return department;
    }

    /**
     * 根据部门id修改部门信息
     * @param department
     * @return
     */
    @Override
    public Department update(Department department) {
        departmentDao.update(department);
        return this.queryById(department.getDepId());
    }

    /**
     * 获取状态为 1 的部门列表
     * @return
     */
    @Override
    public Map<String, Object> queryActiveDepartmentList() {
        Map<String, Object> map = new HashMap<>();
        try{
            List<ActiveDepartmentListResponseDTO> activeDepartmentList = departmentDao.queryActiveDepartmentList();
            map.put("success",true);
            map.put("data",activeDepartmentList);
        }catch(Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
        }
        return map;
    }
}
