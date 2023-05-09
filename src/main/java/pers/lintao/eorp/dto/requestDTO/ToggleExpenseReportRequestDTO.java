package pers.lintao.eorp.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 切换报销单状态的RequestDTO
 * @author: lilintao@163.com
 * @time: 2023/4/27 11时10分 周三
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToggleExpenseReportRequestDTO implements Serializable {
    private static final long serialVersionUID = -7595290991974695904L;
    /**
     *  报销单id
     */
    private Integer expenseId;
    /**
     * 报销单总金额
     */
    private Integer totalAmount;
    /**
     *状态 0 审批单初始状态,1 部门经理审批通过,2 总经理审批通过,3 财务审核通过,-1 部门经理打回,-2 总经理打回,-3 财务打回
     */
    private Integer status;
    /**
     * 处理人id
     */
    private Integer emId;
    /**
     * 当前处理人的职位id
     */
    private Integer positionId;
    /**
     * 审批操作: pass通过,repulse打回
     */
    private String examine;

}
