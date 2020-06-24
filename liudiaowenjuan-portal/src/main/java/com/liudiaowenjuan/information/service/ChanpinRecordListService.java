package com.liudiaowenjuan.information.service;

import java.util.List;
import java.util.Map;

import com.liudiaowenjuan.information.domain.ChanpinRecordListDO;

/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-06-17 18:16:53
 */
public interface ChanpinRecordListService {
	
	ChanpinRecordListDO get(Integer id);
	
	List<ChanpinRecordListDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ChanpinRecordListDO chanpinRecordList);
	
	int update(ChanpinRecordListDO chanpinRecordList);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
