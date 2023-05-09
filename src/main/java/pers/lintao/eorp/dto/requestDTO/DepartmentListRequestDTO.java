package pers.lintao.eorp.dto.requestDTO;

import pers.lintao.eorp.dto.PageDTO;
import java.io.Serializable;

/**
 * @description: 获取部门列表RequestDTO
 *
 * @author: lilintao@163.com
 * @time: 2023/4/26 15时42分 周二
 */
public class DepartmentListRequestDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -7244913361524907877L;

    private String name;

    private String address;

    private Integer status;

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
