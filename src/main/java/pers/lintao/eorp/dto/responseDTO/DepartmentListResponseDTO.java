package pers.lintao.eorp.dto.responseDTO;

import java.io.Serializable;

/**
 * @description: 部门列表ResponseDTO
 * @author: lilintao@163.com
 * @time: 2023/4/26 15时55分 周二
 */
public class DepartmentListResponseDTO implements Serializable {

    private static final long serialVersionUID = -5541513879122425615L;

    /**
     * 部门id
     */
    private Integer depId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 办公地点
     */
    private String address;
    /**
     * 1代表有效,0代表无效
     */
    private Integer status;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
