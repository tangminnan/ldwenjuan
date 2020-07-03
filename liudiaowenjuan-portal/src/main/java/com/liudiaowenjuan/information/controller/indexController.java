package com.liudiaowenjuan.information.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liudiaowenjuan.common.utils.R;
import com.liudiaowenjuan.information.domain.ChanpinDetailsDO;
import com.liudiaowenjuan.information.domain.ChanpinListDO;
import com.liudiaowenjuan.information.domain.ChanpinRecordDetailsDO;
import com.liudiaowenjuan.information.domain.ChanpinRecordListDO;
import com.liudiaowenjuan.information.domain.ChanpinTitleChooseDO;
import com.liudiaowenjuan.information.service.ChanpinDetailsService;
import com.liudiaowenjuan.information.service.ChanpinListService;
import com.liudiaowenjuan.information.service.ChanpinRecordDetailsService;
import com.liudiaowenjuan.information.service.ChanpinRecordListService;
import com.liudiaowenjuan.information.service.ChanpinTitleChooseService;

@Controller
public class indexController {
	
	@Autowired
	ChanpinListService chanpinListService;
	@Autowired
	ChanpinDetailsService chanpinDetailsService;
	@Autowired
	ChanpinTitleChooseService chanpinTitleChooseService;
	@Autowired
	ChanpinRecordListService chanpinRecordListService;
	@Autowired
	ChanpinRecordDetailsService chanpinRecordDetailsService;
	
	@GetMapping("/index")
	String index(Model model){
		Map<String,Object> map = new HashMap<>();
		map.put("status", "1");
 		List<ChanpinListDO> list = chanpinListService.list(map);
 		model.addAttribute("list",list);
		return "information/wenjuan4";
	}
	
	@GetMapping("/wenjuan/shouye")
	String shouye(Model model,Integer id){
		model.addAttribute("cplist",chanpinListService.get(id));
		model.addAttribute("id",id);
		return "information/shouye";
	}
	
	
	@GetMapping("/wenjian/kaishidati")
	String kaishidati(Model model,Integer id,String uname, String identityCard, String phone,String sturelation){
		model.addAttribute("id",id);
		model.addAttribute("uname",uname);
		model.addAttribute("identityCard",identityCard);
		model.addAttribute("phone",phone);
		model.addAttribute("sturelation",sturelation);
		
		Map<String,Object> map = new HashMap<>();
		map.put("chanpinId", id);
		int list = chanpinDetailsService.count(map);
		model.addAttribute("cpchang",list);
		return "information/wenjuan1";
	}
	
	@GetMapping("/querywenjuan/chanpinDetails")
	@ResponseBody
	List<ChanpinDetailsDO> chanpinDetails(Integer id){
		Map<String,Object> map = new HashMap<>();
		map.put("chanpinId", id);
		List<ChanpinDetailsDO> list = chanpinDetailsService.list(map);
		return list;
		
	}
	
	@GetMapping("/querywenjuan/titleDetails")
	@ResponseBody
	List<ChanpinTitleChooseDO> titleDetails(Integer id){
		Map<String,Object> map = new HashMap<>();
		map.put("timuId", id);
		List<ChanpinTitleChooseDO> titleChooseDO = chanpinTitleChooseService.list(map);
		return titleChooseDO;
		
	}
	
	@PostMapping("/save/datishuju")
	@ResponseBody
	Map<String,Object> datishuju(@RequestBody ChanpinRecordListDO chanpin){
		Map<String,Object> map = new HashMap<>();
		ChanpinRecordListDO crl = new ChanpinRecordListDO();
		crl.setChanpinId(chanpin.getChanpinId());
		crl.setChanpinName(chanpinListService.get(chanpin.getChanpinId()).getChanpinName());
		crl.setAddTime(new Date());
		if(chanpinRecordListService.save(crl)>0){
			List<ChanpinRecordDetailsDO> titlexinxi = chanpin.getTitlexinxi();
			for (ChanpinRecordDetailsDO xinxi : titlexinxi) {
				ChanpinRecordDetailsDO crd = new ChanpinRecordDetailsDO();
				crd.setRecordId(crl.getId());
				crd.setTitleId(0);
				crd.setTitleName(xinxi.getTitleName());
				crd.setTitleType(1);
				crd.setRemarks(xinxi.getRemarks());
				crd.setAddTime(new Date());
				chanpinRecordDetailsService.save(crd);
			}
			List<ChanpinRecordDetailsDO> titlelist = chanpin.getTitlelist();
			for (ChanpinRecordDetailsDO chanpinRecordDetailsDO : titlelist) {
				if(chanpinRecordDetailsDO.getTitleType() != 4){
					ChanpinRecordDetailsDO crd2 = new ChanpinRecordDetailsDO();
					crd2.setRecordId(crl.getId());
					crd2.setTitleId(chanpinRecordDetailsDO.getTitleId());
					crd2.setTitleName(chanpinRecordDetailsDO.getTitleName());
					crd2.setTitleType(chanpinRecordDetailsDO.getTitleType());
					crd2.setRemarks(chanpinRecordDetailsDO.getRemarks());
					crd2.setAddTime(new Date());
					chanpinRecordDetailsService.save(crd2);
				}
			}
			
			
		}
		
		map.put("code", "0");
		map.put("msg", "操作成功");
		return map;
		
	}
	
	@GetMapping("/shaichawenjuan/jiazai")
	String jiazai(){
		return "information/jiazaiye";
	}
	
	@GetMapping("/shaichawenjuan/tijiaochenggong")
	String chenggong(){
		return "information/chenggong";
	}
	
	
}
