package pers.lintao.eorp.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.lintao.eorp.dto.requestDTO.PositionListRequestDTO;
import pers.lintao.eorp.dto.responseDTO.ActivePositionListResponseDTO;
import pers.lintao.eorp.dto.responseDTO.PositiontListResponseDTO;
import pers.lintao.eorp.entity.Position;

import java.util.List;

/**
 * 职位表(Position)表数据库访问层
 *
 * @author 29Ring
 * @since 2023-04-24 13:10:12
 */
@Mapper
public interface PositionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param positionId 主键
     * @return 实例对象
     */
    Position queryById(Integer positionId);


    /**
     * 统计总行数
     *
     * @param position 查询条件
     * @return 总行数
     */
    long count(Position position);

    /**
     * 新增数据
     *
     * @param position 实例对象
     * @return 影响行数
     */
    int insert(Position position);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Position> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Position> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Position> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Position> entities);

    /**
     * 修改数据
     *
     * @param position 实例对象
     * @return 影响行数
     */
    int update(Position position);

    /**
     * 通过主键删除数据
     *
     * @param positionId 主键
     * @return 影响行数
     */
    int deleteById(Integer positionId);

    /**
     * MyBatis-plus分页查询
     * @param page
     * @param wrapper
     * @return
     */
    IPage<PositiontListResponseDTO> queryPageList(Page<PositionListRequestDTO> page, @Param("ew") QueryWrapper<PositionListRequestDTO> wrapper);

    void updateFailureStatusById(Integer positionId);

    void updateSuccessStatusById(Integer positionId);

    List<ActivePositionListResponseDTO> queryActivePositionList();
}

