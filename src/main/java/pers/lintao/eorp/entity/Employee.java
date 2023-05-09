package pers.lintao.eorp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 员工表(Employee)实体类
 *
 * @author lilintao@163.com
 * @since 2023-04-24 10:19:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = -24609236399751243L;
    /**
     * 员工id
     */
    @TableId(type = IdType.AUTO)
    private Integer emId;
    /**
     * 密码
     */
    private String password;
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
}

