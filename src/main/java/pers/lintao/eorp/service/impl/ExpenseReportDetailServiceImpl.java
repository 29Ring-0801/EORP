package pers.lintao.eorp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.lintao.eorp.dao.ExpenseReportDetailDao;
import pers.lintao.eorp.dto.requestDTO.ExpenseReportRequestDTO;
import pers.lintao.eorp.entity.ExpenseReportDetail;
import pers.lintao.eorp.service.ExpenseReportDetailService;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报销单细节表(ExpenseReportDetail)表服务实现类
 *
 * @author lilintao@163.com
 * @since 2023-04-30 14:55:31
 */
@Service("expenseReportDetailService")
public class ExpenseReportDetailServiceImpl implements ExpenseReportDetailService {
    @Resource
    private ExpenseReportDetailDao expenseReportDetailDao;


    /**
     * 通过ID查询单条数据
     *
     * @param expensiveDetailId 主键
     * @return 实例对象
     */
    @Override
    public ExpenseReportDetail queryById(Integer expensiveDetailId) {
        return this.expenseReportDetailDao.queryById(expensiveDetailId);
    }



    /**
     * 新增数据
     *
     * @param expenseReportDetail 实例对象
     * @return 实例对象
     */
    @Override
    public ExpenseReportDetail insert(ExpenseReportDetail expenseReportDetail) {
        this.expenseReportDetailDao.insert(expenseReportDetail);
        return expenseReportDetail;
    }

    /**
     * 修改数据
     *
     * @param requestDTO 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public Map<String,Object> update(ExpenseReportRequestDTO requestDTO) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            expenseReportDetailDao.deleteByExpenseId(requestDTO.getExpenseReport().getExpenseId());
            expenseReportDetailDao.insertBatch(requestDTO.getDetailList());
        }catch (Exception e){
            map.put("success", false);
            map.put("errMsg", "报销凭证上传失败"+e.getMessage());
            throw new RuntimeException("报销单详情数据更新错误");
        }

        return map;
    }

    /**
     * 通过主键删除数据
     *
     * @param expensiveDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer expensiveDetailId) {
        return this.expenseReportDetailDao.deleteById(expensiveDetailId) > 0;
    }

    /**
     * 批量新增
     * @param detailList
     */
    @Override
    public void insertBatch(List<ExpenseReportDetail> detailList) {
        int i = expenseReportDetailDao.insertBatch(detailList);
    }

    /**
     * 根据报销单id查询报销单详情
     * @param expenseId
     * @return
     */
    @Override
    public List<ExpenseReportDetail> queryByExpenseId(Integer expenseId) {
        return expenseReportDetailDao.queryByExpenseId(expenseId);
    }

    /**
     * 根据报销单id删除
     * @param expenseId
     */
    @Override
    public void deleteByExpenseId(Integer expenseId) {
        expenseReportDetailDao.deleteByExpenseId(expenseId);
    }
}
