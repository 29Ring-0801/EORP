package pers.lintao.eorp.service;

import pers.lintao.eorp.entity.DealRecord;

/**
 * 操作记录表(DealRecord)表服务接口
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
public interface DealRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param recordId 主键
     * @return 实例对象
     */
    DealRecord queryById(Integer recordId);


    /**
     * 新增数据
     *
     * @param dealRecord 实例对象
     * @return 实例对象
     */
    DealRecord insert(DealRecord dealRecord);

    /**
     * 修改数据
     *
     * @param dealRecord 实例对象
     * @return 实例对象
     */
    DealRecord update(DealRecord dealRecord);

    /**
     * 通过主键删除数据
     *
     * @param recordId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer recordId);

}
