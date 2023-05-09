package pers.lintao.eorp.entity;

import java.io.Serializable;

/**
 * 部门表(Department)实体类
 *
 * @author lilintao@163.com
 * @since 2023-04-24 12:21:27
 */
public class Department implements Serializable {
    private static final long serialVersionUID = -35695796682471848L;
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

    @Override
    public String toString() {
        return "Department{" +
                "depId=" + depId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}

