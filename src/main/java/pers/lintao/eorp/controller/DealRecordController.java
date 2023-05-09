package pers.lintao.eorp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.lintao.eorp.entity.DealRecord;
import pers.lintao.eorp.service.DealRecordService;
import javax.annotation.Resource;

/**
 * 操作记录表(DealRecord)表控制层
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
@RestController
@RequestMapping("dealRecord")
public class DealRecordController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private DealRecordService dealRecordService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<DealRecord> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.dealRecordService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dealRecord 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<DealRecord> add(DealRecord dealRecord) {
        return ResponseEntity.ok(this.dealRecordService.insert(dealRecord));
    }

    /**
     * 编辑数据
     *
     * @param dealRecord 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<DealRecord> edit(DealRecord dealRecord) {
        return ResponseEntity.ok(this.dealRecordService.update(dealRecord));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.dealRecordService.deleteById(id));
    }

}

