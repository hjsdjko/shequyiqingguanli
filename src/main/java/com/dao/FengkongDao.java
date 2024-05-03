package com.dao;

import com.entity.FengkongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.FengkongView;

/**
 * 隔离信息 Dao 接口
 *
 * @author 
 */
public interface FengkongDao extends BaseMapper<FengkongEntity> {

   List<FengkongView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
