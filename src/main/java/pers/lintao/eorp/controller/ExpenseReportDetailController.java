package pers.lintao.eorp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.lintao.eorp.entity.ExpenseReportDetail;
import pers.lintao.eorp.service.ExpenseReportDetailService;
import javax.annotation.Resource;

/**
 * 报销单细节表(ExpenseReportDetail)表控制层
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
@RestController
@RequestMapping("expenseReportDetail")
public class ExpenseReportDetailController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private ExpenseReportDetailService expenseReportDetailService;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ExpenseReportDetail> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.expenseReportDetailService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param expenseReportDetail 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ExpenseReportDetail> add(ExpenseReportDetail expenseReportDetail) {
        return ResponseEntity.ok(this.expenseReportDetailService.insert(expenseReportDetail));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.expenseReportDetailService.deleteById(id));
    }

}

