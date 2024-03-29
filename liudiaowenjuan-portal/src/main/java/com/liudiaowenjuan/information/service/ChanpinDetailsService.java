package com.liudiaowenjuan.information.service;

import java.util.List;
import java.util.Map;

import com.liudiaowenjuan.information.domain.ChanpinDetailsDO;

/**
 * 
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-06-17 18:16:52
 */
public interface ChanpinDetailsService {
	
	ChanpinDetailsDO get(Integer id);
	
	List<ChanpinDetailsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ChanpinDetailsDO chanpinDetails);
	
	int update(ChanpinDetailsDO chanpinDetails);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
