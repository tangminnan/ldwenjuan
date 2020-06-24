package com.liudiaowenjuan.common.controller;

import org.springframework.stereotype.Controller;

import com.liudiaowenjuan.common.utils.ShiroUtils;
import com.liudiaowenjuan.owneruser.domain.OwnerUserDO;
import com.liudiaowenjuan.system.domain.UserToken;

@Controller
public class BaseController {
	public OwnerUserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getChectorId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
	/*public Long getforIds() {
		return getUser().getUserId();
	}*/
}