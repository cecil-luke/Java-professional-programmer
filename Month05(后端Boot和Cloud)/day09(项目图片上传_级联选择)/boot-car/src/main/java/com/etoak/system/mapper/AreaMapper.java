package com.etoak.system.mapper;

import com.etoak.common.vo.AreaVO;
import com.etoak.system.entity.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author et2301
 * @since 2023-05-15
 */
public interface AreaMapper extends BaseMapper<Area> {

  /**
   * 省市区列表数据
   *
   * @return
   */
  List<AreaVO> getList();

  /**
   * 根据id列表查询名称列表
   *
   * @param idList
   */
  List<String> getNameList(@Param("idList") List<Integer> idList);

}
