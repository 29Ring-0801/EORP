package pers.lintao.eorp.dto.requestDTO;

import pers.lintao.eorp.entity.ExpenseReport;
import pers.lintao.eorp.entity.ExpenseReportDetail;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 新增报销单RequestDTO
 * @author: lilintao@163.com
 * @time: 2023/4/1 10时58分 周三
 */
public class ExpenseReportRequestDTO implements Serializable {

    private static final long serialVersionUID = -3319830281023430305L;

    private ExpenseReport expenseReport;

    private List<ExpenseReportDetail> detailList;

    public ExpenseReport getExpenseReport() {
        return expenseReport;
    }

    public void setExpenseReport(ExpenseReport expenseReport) {
        this.expenseReport = expenseReport;
    }

    public List<ExpenseReportDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ExpenseReportDetail> detailList) {
        this.detailList = detailList;
    }
}
