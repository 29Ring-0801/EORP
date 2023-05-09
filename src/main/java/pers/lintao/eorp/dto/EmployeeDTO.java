package pers.lintao.eorp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.lintao.eorp.entity.Department;
import pers.lintao.eorp.entity.Position;

import java.io.Serializable;

/**
 * @description: Employee员工类数据传输对象
 * @author: lilintao@163.com
 * @time: 2023/4/24 10时44分 周日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = 3336766464838364654L;
    /**
     * 员工id
     */
    private Integer emId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String loginName;
    /**
     * 部门id
     */
    private Integer depId;
    /**
     * 职位id
     */
    private Integer positionId;
    /**
     * 状态(0:离职 1:在职)
     */
    private Integer status;
    /**
     * 一对一关联部门
     */
    private Department department;
    /**
     * 一对一关联职位
     */
    private Position position;
}
