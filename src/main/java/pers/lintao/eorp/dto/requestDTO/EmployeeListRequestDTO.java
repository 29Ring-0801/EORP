package pers.lintao.eorp.dto.requestDTO;

import pers.lintao.eorp.dto.PageDTO;

import java.io.Serializable;

/**
 * @description:
 * @author: lintao
 * @time: 2023/4/10 11时52分 周二
 */
public class EmployeeListRequestDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 39317114287995870L;
    /**
     * 姓名
     */
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
