package com.liudiaowenjuan.information.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.liudiaowenjuan.information.domain.ChanpinTitleChooseDO;

/**
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-06-17 18:16:53
 */
@Mapper
public interface ChanpinTitleChooseDao {

	ChanpinTitleChooseDO get(Integer id);
	
	List<ChanpinTitleChooseDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ChanpinTitleChooseDO chanpinTitleChoose);
	
	int update(ChanpinTitleChooseDO chanpinTitleChoose);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
