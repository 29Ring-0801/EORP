package pers.lintao.eorp.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作记录数据传输对象实体类
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealRecordRequestDTO implements Serializable {

    private static final long serialVersionUID = -2763572567646294287L;
    /**
     * 操作记录表id
     */
    private Integer recordId;
    /**
     * 报销单id
     */
    private Integer expensiveId;
    /**
     * 操作员工id
     */
    private Integer emId;
    /**
     * 职位id
     */
    private Integer positionId;
    /**
     * 处理时间
     */
    private Date dealTime;
    /**
     * 处理方式
     */
    private String dealWay;
    /**
     * 处理结果
     */
    private String dealResult;
    /**
     * 处理结果备注
     */
    private String comment;
}

