package pers.lintao.eorp.dto.requestDTO;


import pers.lintao.eorp.dto.PageDTO;

import java.io.Serializable;

/**
 * @description: 获取职位列表RequestDTO
 * @author: lilintao@163.com
 * @time: 2023/4/26 15时42分 周二
 */
public class PositionListRequestDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 6825418523757556899L;

    private String positionName;

    private Integer status;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
