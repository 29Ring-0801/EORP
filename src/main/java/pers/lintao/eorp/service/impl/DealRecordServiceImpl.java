package pers.lintao.eorp.service.impl;

import org.springframework.stereotype.Service;
import pers.lintao.eorp.dao.DealRecordDao;
import pers.lintao.eorp.entity.DealRecord;
import pers.lintao.eorp.service.DealRecordService;
import javax.annotation.Resource;

/**
 * 操作记录表(DealRecord)表服务实现类
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
@Service("dealRecordService")
public class DealRecordServiceImpl implements DealRecordService {
    @Resource
    private DealRecordDao dealRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param recordId 主键
     * @return 实例对象
     */
    @Override
    public DealRecord queryById(Integer recordId) {
        return this.dealRecordDao.queryById(recordId);
    }

    /**
     * 新增数据
     *
     * @param dealRecord 实例对象
     * @return 实例对象
     */
    @Override
    public DealRecord insert(DealRecord dealRecord) {
        this.dealRecordDao.insert(dealRecord);
        return dealRecord;
    }

    /**
     * 修改数据
     *
     * @param dealRecord 实例对象
     * @return 实例对象
     */
    @Override
    public DealRecord update(DealRecord dealRecord) {
        this.dealRecordDao.update(dealRecord);
        return this.queryById(dealRecord.getRecordId());
    }

    /**
     * 通过主键删除数据
     *
     * @param recordId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer recordId) {
        return this.dealRecordDao.deleteById(recordId) > 0;
    }
}
