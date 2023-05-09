package pers.lintao.eorp.dto.requestDTO;


import java.io.Serializable;

/**
 * @description:
 * @author: lilintao@163.com
 * @time: 2023/4/10 09时09分 周二
 */
public class TogglePositionRequestDTO implements Serializable {

    private static final long serialVersionUID = 5191984384552114563L;

    private Integer positionId;

    private Integer status;

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
