package com.liudiaowenjuan.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liudiaowenjuan.information.dao.ChanpinRecordDetailsDao;
import com.liudiaowenjuan.information.domain.ChanpinRecordDetailsDO;
import com.liudiaowenjuan.information.service.ChanpinRecordDetailsService;

import java.util.List;
import java.util.Map;



@Service
public class ChanpinRecordDetailsServiceImpl implements ChanpinRecordDetailsService {
	@Autowired
	private ChanpinRecordDetailsDao chanpinRecordDetailsDao;
	
	@Override
	public ChanpinRecordDetailsDO get(Integer id){
		return chanpinRecordDetailsDao.get(id);
	}
	
	@Override
	public List<ChanpinRecordDetailsDO> list(Map<String, Object> map){
		return chanpinRecordDetailsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return chanpinRecordDetailsDao.count(map);
	}
	
	@Override
	public int save(ChanpinRecordDetailsDO chanpinRecordDetails){
		return chanpinRecordDetailsDao.save(chanpinRecordDetails);
	}
	
	@Override
	public int update(ChanpinRecordDetailsDO chanpinRecordDetails){
		return chanpinRecordDetailsDao.update(chanpinRecordDetails);
	}
	
	@Override
	public int remove(Integer id){
		return chanpinRecordDetailsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return chanpinRecordDetailsDao.batchRemove(ids);
	}
	
}
