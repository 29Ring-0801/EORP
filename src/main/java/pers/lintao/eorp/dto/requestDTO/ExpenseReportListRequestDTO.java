package pers.lintao.eorp.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.lintao.eorp.dto.PageDTO;
import java.io.Serializable;

/**
 * @description: 获取报销单列表RequestDTO
 * @author: lilintao@163.com
 * @time: 2023/4/7 15时42分 周二
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseReportListRequestDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1231583141493586437L;

    private Integer emId;

    private Integer status;

}
