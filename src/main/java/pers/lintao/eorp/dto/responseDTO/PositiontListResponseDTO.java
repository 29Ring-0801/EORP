package pers.lintao.eorp.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 职位列表ResponseDTO
 * @author: lilintao@163.com
 * @time: 2023/4/26 15时55分 周二
 */
public class PositiontListResponseDTO implements Serializable {

    private static final long serialVersionUID = -795130274943521569L;

    private Integer positionId;

    private String positionName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 1代表有效,0代表无效
     */
    private Integer status;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
