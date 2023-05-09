package pers.lintao.eorp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.lintao.eorp.dao.PositionDao;
import pers.lintao.eorp.dto.requestDTO.PositionListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.TogglePositionRequestDTO;
import pers.lintao.eorp.dto.responseDTO.ActivePositionListResponseDTO;
import pers.lintao.eorp.dto.responseDTO.PositiontListResponseDTO;
import pers.lintao.eorp.entity.Position;
import pers.lintao.eorp.service.PositionService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 职位表(Position)表服务实现类
 *
 * @author lilintao@163.com
 * @since 2023-04-24 12:27:38
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;

    /**
     * 查询职位列表
     * @param requestDTO
     * @return
     */
    @Override
    public Map<String, Object> queryPageList(PositionListRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            //封装查询条件
            QueryWrapper<PositionListRequestDTO> wrapper = new QueryWrapper<>();
            if (null != requestDTO.getPositionName()){
                wrapper.like("position_name", requestDTO.getPositionName());
            }
            if (null != requestDTO.getStatus()){
                wrapper.eq("status", requestDTO.getStatus());
            }
            Page<PositionListRequestDTO> page = new Page<>(requestDTO.getCurrent(),requestDTO.getSize());
            IPage<PositiontListResponseDTO> positions = positionDao.queryPageList(page, wrapper);
            map.put("success",true);
            map.put("data",positions);
        }catch (Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
            //如果用到了声明式事务,在catch中捕获异常之后,一定要抛出,否则事务不会回滚
        }
        return map;
    }

    /**
     * 对单个职位状态进行修改
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Map<String, Object> toggleStatus(TogglePositionRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        Integer status = requestDTO.getStatus();
        Integer positionId = requestDTO.getPositionId();
        try {
            if (1 == status){
                //将状态改为禁用:0
                positionDao.updateFailureStatusById(positionId);
                //测试事务回滚
                //throw new Exception("状态修改异常");
            }else {
                //将状态改为启用:1
                positionDao.updateSuccessStatusById(positionId);
            }
            map.put("success", true);
        }catch (Exception e){
            //TODO:如果用到了声明式事务,在catch中捕获异常之后,一定要抛出,否则事务不会回滚
            throw e;
        }
        return map;
    }

    @Override
    public Position insert(Position position) {
        //给字段加创建时间方式一,方式二在mapper映射文件中将#{createTime}改为now()函数,方式三去掉SQL语句中的createTime字段,修改表结构给时间默认值
        position.setCreateTime(new Date());
        positionDao.insert(position);
        return position;
    }

    @Override
    public Position queryById(Integer positionId) {
        Position position = positionDao.queryById(positionId);
        return position;
    }

    @Override
    public Position update(Position position) {
        positionDao.update(position);
        return positionDao.queryById(position.getPositionId());
    }


    /**
     * 获取状态为 1 的部门列表
     * @return
     */
    @Override
    public Map<String, Object> queryActivePositionList() {
        Map<String, Object> map = new HashMap<>();
        try{
            List<ActivePositionListResponseDTO> activePositionList = positionDao.queryActivePositionList();
            map.put("success",true);
            map.put("data",activePositionList);
        }catch(Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
        }
        return map;
    }
}
