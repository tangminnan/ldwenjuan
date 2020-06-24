package com.liudiaowenjuan.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liudiaowenjuan.information.dao.ChanpinDetailsDao;
import com.liudiaowenjuan.information.domain.ChanpinDetailsDO;
import com.liudiaowenjuan.information.service.ChanpinDetailsService;

import java.util.List;
import java.util.Map;



@Service
public class ChanpinDetailsServiceImpl implements ChanpinDetailsService {
	@Autowired
	private ChanpinDetailsDao chanpinDetailsDao;
	
	@Override
	public ChanpinDetailsDO get(Integer id){
		return chanpinDetailsDao.get(id);
	}
	
	@Override
	public List<ChanpinDetailsDO> list(Map<String, Object> map){
		return chanpinDetailsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return chanpinDetailsDao.count(map);
	}
	
	@Override
	public int save(ChanpinDetailsDO chanpinDetails){
		return chanpinDetailsDao.save(chanpinDetails);
	}
	
	@Override
	public int update(ChanpinDetailsDO chanpinDetails){
		return chanpinDetailsDao.update(chanpinDetails);
	}
	
	@Override
	public int remove(Integer id){
		return chanpinDetailsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return chanpinDetailsDao.batchRemove(ids);
	}
	
}
