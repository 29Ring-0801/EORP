package pers.lintao.eorp.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 修改密码请求DTO
 * @author: lilintao@163.com
 * @time: 2023/4/18 15时23分 周二
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModifyPwdRequestDTO implements Serializable {
    private static final long serialVersionUID = 5993216417693174723L;
    /**
     * 员工id
     */
    private Integer emId;
    /**
     * 旧密码
     */
    private String oldpwd;
    /**
     * 新密码
     */
    private String newpwd;
    /**
     * 确认新密码
     */
    private String confirmpwd;

}
