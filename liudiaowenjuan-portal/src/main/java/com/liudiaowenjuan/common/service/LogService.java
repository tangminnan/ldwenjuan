package com.liudiaowenjuan.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liudiaowenjuan.common.domain.LogDO;
import com.liudiaowenjuan.common.domain.PageDO;
import com.liudiaowenjuan.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
