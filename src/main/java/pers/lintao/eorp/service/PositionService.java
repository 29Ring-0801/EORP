package pers.lintao.eorp.service;

import pers.lintao.eorp.dto.requestDTO.PositionListRequestDTO;
import pers.lintao.eorp.dto.requestDTO.TogglePositionRequestDTO;
import pers.lintao.eorp.entity.Position;

import java.util.Map;

/**
 * 职位表(Position)表服务接口
 *
 * @author lilintao@163.com
 * @since 2023-04-24 12:27:38
 */
public interface PositionService {

    /**
     * 查询职位列表
     * @param requestDTO
     * @return
     */
    Map<String, Object> queryPageList(PositionListRequestDTO requestDTO);

    /**
     * 对单个职位状态进行修改
     * @param requestDTO
     * @return
     */
    Map<String, Object> toggleStatus(TogglePositionRequestDTO requestDTO);

    /**
     * 新增职位
     * @param position
     * @return
     */
    Position insert(Position position);

    /**
     * 查询
     * @param positionId
     * @return
     */
    Position queryById(Integer positionId);

    /**
     * 修改职位信息
     * @param position
     * @return
     */
    Position update(Position position);

    /**
     * 获取状态为 1 的职位列表
     * @return
     */
    Map<String, Object> queryActivePositionList();
}
