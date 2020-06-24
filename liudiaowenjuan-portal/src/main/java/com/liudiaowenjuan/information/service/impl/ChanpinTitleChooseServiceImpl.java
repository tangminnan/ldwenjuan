package com.liudiaowenjuan.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liudiaowenjuan.information.dao.ChanpinTitleChooseDao;
import com.liudiaowenjuan.information.domain.ChanpinTitleChooseDO;
import com.liudiaowenjuan.information.service.ChanpinTitleChooseService;

import java.util.List;
import java.util.Map;



@Service
public class ChanpinTitleChooseServiceImpl implements ChanpinTitleChooseService {
	@Autowired
	private ChanpinTitleChooseDao chanpinTitleChooseDao;
	
	@Override
	public ChanpinTitleChooseDO get(Integer id){
		return chanpinTitleChooseDao.get(id);
	}
	
	@Override
	public List<ChanpinTitleChooseDO> list(Map<String, Object> map){
		return chanpinTitleChooseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return chanpinTitleChooseDao.count(map);
	}
	
	@Override
	public int save(ChanpinTitleChooseDO chanpinTitleChoose){
		return chanpinTitleChooseDao.save(chanpinTitleChoose);
	}
	
	@Override
	public int update(ChanpinTitleChooseDO chanpinTitleChoose){
		return chanpinTitleChooseDao.update(chanpinTitleChoose);
	}
	
	@Override
	public int remove(Integer id){
		return chanpinTitleChooseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return chanpinTitleChooseDao.batchRemove(ids);
	}
	
}
