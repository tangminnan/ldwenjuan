package com.liudiaowenjuan.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liudiaowenjuan.information.dao.ChanpinRecordListDao;
import com.liudiaowenjuan.information.domain.ChanpinRecordListDO;
import com.liudiaowenjuan.information.service.ChanpinRecordListService;

import java.util.List;
import java.util.Map;



@Service
public class ChanpinRecordListServiceImpl implements ChanpinRecordListService {
	@Autowired
	private ChanpinRecordListDao chanpinRecordListDao;
	
	@Override
	public ChanpinRecordListDO get(Integer id){
		return chanpinRecordListDao.get(id);
	}
	
	@Override
	public List<ChanpinRecordListDO> list(Map<String, Object> map){
		return chanpinRecordListDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return chanpinRecordListDao.count(map);
	}
	
	@Override
	public int save(ChanpinRecordListDO chanpinRecordList){
		return chanpinRecordListDao.save(chanpinRecordList);
	}
	
	@Override
	public int update(ChanpinRecordListDO chanpinRecordList){
		return chanpinRecordListDao.update(chanpinRecordList);
	}
	
	@Override
	public int remove(Integer id){
		return chanpinRecordListDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return chanpinRecordListDao.batchRemove(ids);
	}
	
}
