package pers.lintao.eorp.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 切换单个部门状态的RequestDTO
 * @author: lilintao@163.com
 * @time: 2023/4/27 11时10分 周三
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToggleDepartmentRequestDTO implements Serializable {
    private static final long serialVersionUID = -7595290991974695904L;

    private Integer depId;

    private Integer status;
}
