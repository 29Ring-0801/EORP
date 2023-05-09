package pers.lintao.eorp.dto.responseDTO;


import java.io.Serializable;

/**
 * @description: 有效的部门列表ResponseDTO
 * @author: lilintao@163.com
 * @time: 2023/4/11 09时49分 周三
 */
public class ActiveDepartmentListResponseDTO  implements Serializable {

    private static final long serialVersionUID = -411909216657054753L;

    private Integer depId;

    private String name;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
