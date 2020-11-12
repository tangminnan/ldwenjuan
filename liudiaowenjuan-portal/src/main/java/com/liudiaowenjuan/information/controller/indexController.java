package com.liudiaowenjuan.information.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liudiaowenjuan.common.utils.R;
import com.liudiaowenjuan.common.utils.WeiScanUtils2;
import com.liudiaowenjuan.information.domain.ChanpinDetailsDO;
import com.liudiaowenjuan.information.domain.ChanpinListDO;
import com.liudiaowenjuan.information.domain.ChanpinRecordDetailsDO;
import com.liudiaowenjuan.information.domain.ChanpinRecordListDO;
import com.liudiaowenjuan.information.domain.ChanpinTitleChooseDO;
import com.liudiaowenjuan.information.domain.StudentDO;
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
		Map<String,Object> map = WeiScanUtils2.sign("http://ldwenjuan.dddmaker.com/wenjuan/shouye?id="+id);
		model.addAttribute("timestamp",map.get("timestam"));
		model.addAttribute("nonceStr",map.get("nonceSt"));
		model.addAttribute("signature",map.get("signatur"));
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
		return "information/wenjuan5";
	}

	@GetMapping("/wenjian/timuxiangq")
	@ResponseBody
	List<Map<String,Object>> timuxiangq(Integer id){
		List<Map<String,Object>> bb = new ArrayList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<>();
		map1.put("chanpinId",id);
		List<ChanpinDetailsDO> list1 = chanpinDetailsService.list(map1);
		for (ChanpinDetailsDO ard : list1 ) {
			Map<String,Object> map3 = new HashMap<>();
			List<Object> ll = new ArrayList<>();
			Map<String,Object> map2 = new HashMap<>();
			map2.put("timuId",ard.getId());
			ll.add(chanpinTitleChooseService.list(map2));
			map3.put("chanpin",ard);
			map3.put("details",ll);
			bb.add(map3);
		}
		return bb;
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
		List<ChanpinRecordDetailsDO> titlexinxi1 = chanpin.getTitlexinxi();
		for (ChanpinRecordDetailsDO xinxi : titlexinxi1) {
			if(xinxi.getTitleName().equals("身份证号")){
				crl.setIdentityCard(xinxi.getRemarks());
			}
		}
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
				crd.setChooseIds("0");
				crd.setChooseSort(xinxi.getRemarks());
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
					crd2.setChooseIds(chanpinRecordDetailsDO.getChooseIds());
					crd2.setChooseSort(chanpinRecordDetailsDO.getChooseSort());
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
	
	@GetMapping("/wenjuan/shouye/getInfo")
	@ResponseBody
	public StudentDO getInfo(String id){
		id=id.split("JOIN")[1];
		System.out.println("==================================");
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		System.out.println("===================================");
		StudentDO studentDO  =  chanpinListService.getInfo(id);
		if(studentDO.getPhone()!=null){
			studentDO.setPhone(studentDO.getPhone().substring(0,11));
		}
		return studentDO;
	}
	
}
