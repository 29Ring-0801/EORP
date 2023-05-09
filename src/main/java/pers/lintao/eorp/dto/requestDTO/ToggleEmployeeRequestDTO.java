package pers.lintao.eorp.dto.requestDTO;

import java.io.Serializable;

/**
 * @description:
 * @author: lilintao@163.com
 * @time: 2023/4/16 13时08分 周一
 */
public class ToggleEmployeeRequestDTO implements Serializable {
    private static final long serialVersionUID = -1798323740837412540L;

    private Integer emId;

    private Integer status;

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
