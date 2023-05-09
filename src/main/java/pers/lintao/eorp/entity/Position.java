package pers.lintao.eorp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位表(Position)实体类
 *
 * @author 29Ring
 * @since 2023-04-24 12:27:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position implements Serializable {
    private static final long serialVersionUID = -19142141234338308L;
    /**
     * 职位表id
     */
    private Integer positionId;
    /**
     * 职位名
     */
    private String positionName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态 1:有效 0:无效
     */
    private Integer status;
}

