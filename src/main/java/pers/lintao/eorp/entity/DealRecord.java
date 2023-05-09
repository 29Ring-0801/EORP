package pers.lintao.eorp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作记录表(DealRecord)实体类
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
public class DealRecord implements Serializable {
    private static final long serialVersionUID = -92494860906291641L;
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
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getExpensiveId() {
        return expensiveId;
    }

    public void setExpensiveId(Integer expensiveId) {
        this.expensiveId = expensiveId;
    }

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealWay() {
        return dealWay;
    }

    public void setDealWay(String dealWay) {
        this.dealWay = dealWay;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}

