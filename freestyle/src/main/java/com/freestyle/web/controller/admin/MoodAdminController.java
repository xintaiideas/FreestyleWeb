package com.freestyle.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.freestyle.domain.view.core.MoodVo;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IMoodService;

@Controller
@RequestMapping("/admin/mood")
public class MoodAdminController {
	
	@Autowired
	private IMoodService moodService;

	@RequestMapping("")
	public String index(
			@RequestParam(required=false,defaultValue="1") Integer pageIndex, 
			@RequestParam(required=false,defaultValue="9") Integer pageSize, 
			Model model
			) {
		Page<MoodVo> moods = this.moodService.pageList(null,pageIndex,pageSize);
		model.addAttribute("moods", moods);
		return "admin/mood";
	}
	
}
