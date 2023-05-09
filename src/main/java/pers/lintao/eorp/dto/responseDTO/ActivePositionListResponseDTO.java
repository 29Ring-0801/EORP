package pers.lintao.eorp.dto.responseDTO;


import java.io.Serializable;

/**
 * @description: 有效的职位列表ResponseDTO
 * @author: lilintao@163.com
 * @time: 2023/4/16 09时18分 周一
 */
public class ActivePositionListResponseDTO implements Serializable {
    private static final long serialVersionUID = 6597463539148335482L;

    private Integer positionId;

    private String positionName;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
