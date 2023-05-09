package pers.lintao.eorp.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.lintao.eorp.entity.Employee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 部门列表ResponseDTO
 * @author: lilintao@163.com
 * @time: 2023/4/26 15时55分 周二
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseReportListResponseDTO implements Serializable {
    private static final long serialVersionUID = -4617413304791415901L;
    /**
     * 报销单id
     */
    private Integer expenseId;
    /**
     * 报销原因
     */
    private String cause;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 待处理人
     */
    private Employee nextDealEm;
    /**
     * 报销总金额
     */
    private BigDecimal totalAmount;
    /**
     * 状态 已处理--1,未处理--0
     */
    private Integer status;
}
