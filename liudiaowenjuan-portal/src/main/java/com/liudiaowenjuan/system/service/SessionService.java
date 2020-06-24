package com.liudiaowenjuan.system.service;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.liudiaowenjuan.owneruser.domain.OwnerUserDO;
import com.liudiaowenjuan.system.domain.UserOnline;

@Service
public interface SessionService {
	List<UserOnline> list();

	List<OwnerUserDO> listOnlineUser();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
