package com.liudiaowenjuan.information.service;

import java.util.List;
import java.util.Map;

import com.liudiaowenjuan.information.domain.ChanpinListDO;
import com.liudiaowenjuan.information.domain.StudentDO;

/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-06-17 18:16:53
 */
public interface ChanpinListService {
	
	ChanpinListDO get(Integer id);
	
	List<ChanpinListDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ChanpinListDO chanpinList);
	
	int update(ChanpinListDO chanpinList);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	StudentDO getInfo(String id);
}
