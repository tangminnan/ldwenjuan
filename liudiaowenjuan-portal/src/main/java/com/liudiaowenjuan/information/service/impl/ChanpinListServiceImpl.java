package com.liudiaowenjuan.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liudiaowenjuan.information.dao.ChanpinListDao;
import com.liudiaowenjuan.information.domain.ChanpinListDO;
import com.liudiaowenjuan.information.service.ChanpinListService;

import java.util.List;
import java.util.Map;



@Service
public class ChanpinListServiceImpl implements ChanpinListService {
	@Autowired
	private ChanpinListDao chanpinListDao;
	
	@Override
	public ChanpinListDO get(Integer id){
		return chanpinListDao.get(id);
	}
	
	@Override
	public List<ChanpinListDO> list(Map<String, Object> map){
		return chanpinListDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return chanpinListDao.count(map);
	}
	
	@Override
	public int save(ChanpinListDO chanpinList){
		return chanpinListDao.save(chanpinList);
	}
	
	@Override
	public int update(ChanpinListDO chanpinList){
		return chanpinListDao.update(chanpinList);
	}
	
	@Override
	public int remove(Integer id){
		return chanpinListDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return chanpinListDao.batchRemove(ids);
	}
	
}
