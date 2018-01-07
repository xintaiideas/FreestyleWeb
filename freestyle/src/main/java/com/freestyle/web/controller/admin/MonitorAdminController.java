package com.freestyle.web.controller.admin;

import java.util.List;

import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.service.admin.IMonitorService;

/**
 * 服务器监控http接口
 * @author 大爱阳哥
 *
 */
@Controller
@RequestMapping("/admin/monitor")
public class MonitorAdminController {
	
	/*监控服务*/
	@Autowired
	private IMonitorService monitorService;

	/**
	 * 打开服务器监控页面
	 * @param model
	 * @return
	 * @throws SigarException
	 */
	@RequestMapping("")
	public String index(Model model) throws SigarException {
		List<String> list = this.monitorService.getIfNames();
		model.addAttribute("ifnames", list);
		return "admin/monitor";
	}
	
	/**
	 * 获取cpu使用率
	 * @return
	 * @throws SigarException
	 */
	@RequestMapping("get-cpu-used")
	public @ResponseBody ApiResponse getCpuUsed() throws SigarException {
		return ApiResponse.buildOK("获取成功").putAttribute("used", this.monitorService.getCpuUsed());
	}
	
	/**
	 * 获取内存占用率
	 * @return
	 * @throws SigarException
	 */
	@RequestMapping("get-mem-used")
	public @ResponseBody ApiResponse getMemUsed() throws SigarException {
		return ApiResponse.buildOK("获取成功").putAttribute("used", this.monitorService.getMemoryUsed());
	}
	
	/**
	 * 获取网络收发字节数
	 * @param name
	 * @return
	 * @throws SigarException
	 */
	@RequestMapping("get-rx-bytes/{name}")
	public @ResponseBody ApiResponse getNetRxBytes(@PathVariable("name") String name) throws SigarException {
		return ApiResponse.buildOK("获取成功").putAttribute("rxBytes", this.monitorService.getNetBytes(name));
	}

}
