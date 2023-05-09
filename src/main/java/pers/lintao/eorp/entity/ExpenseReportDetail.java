package pers.lintao.eorp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 报销单细节表(ExpenseReportDetail)实体类
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
public class ExpenseReportDetail implements Serializable {
    private static final long serialVersionUID = 109106108221892246L;
    /**
     * 报销单细节表id
     */
    private Integer expensiveDetailId;
    /**
     * 报销单id
     */
    private Integer expensiveId;
    /**
     * 报销项目
     */
    private String item;
    /**
     * 费用明细
     */
    private BigDecimal amount;
    /**
     * 费用备注
     */
    private String comment;

    public ExpenseReportDetail() {
    }

    public ExpenseReportDetail(Integer expensiveDetailId, Integer expensiveId, String item, BigDecimal amount, String comment) {
        this.expensiveDetailId = expensiveDetailId;
        this.expensiveId = expensiveId;
        this.item = item;
        this.amount = amount;
        this.comment = comment;
    }

    public Integer getExpensiveDetailId() {
        return expensiveDetailId;
    }

    public void setExpensiveDetailId(Integer expensiveDetailId) {
        this.expensiveDetailId = expensiveDetailId;
    }

    public Integer getExpensiveId() {
        return expensiveId;
    }

    public void setExpensiveId(Integer expensiveId) {
        this.expensiveId = expensiveId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}

