package com.liudiaowenjuan.information.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.liudiaowenjuan.information.domain.ChanpinRecordListDO;

/**
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-06-17 18:16:53
 */
@Mapper
public interface ChanpinRecordListDao {

	ChanpinRecordListDO get(Integer id);
	
	List<ChanpinRecordListDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ChanpinRecordListDO chanpinRecordList);
	
	int update(ChanpinRecordListDO chanpinRecordList);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
