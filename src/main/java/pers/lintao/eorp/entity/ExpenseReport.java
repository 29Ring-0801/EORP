package pers.lintao.eorp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 报销单表(ExpenseReport)实体类
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseReport implements Serializable {
    private static final long serialVersionUID = -93763874978254808L;
    /**
     * 报销单id
     */
    private Integer expenseId;
    /**
     * 报销原因
     */
    private String cause;
    /**
     * 创建人
     */
    private Integer emId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 待处理人
     */
    private Integer nextDealEm;
    /**
     * 报销总金额
     */
    private BigDecimal totalAmount;
    /**
     * 状态 已处理--1,未处理--0
     */
    private Integer status;
    /**
     * 报销凭证文件名
     */
    private String expenseVoucher;

}

