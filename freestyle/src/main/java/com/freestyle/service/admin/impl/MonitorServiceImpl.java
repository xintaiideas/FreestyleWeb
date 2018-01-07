package com.freestyle.service.admin.impl;

import java.util.List;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Service;

import scala.actors.threadpool.Arrays;

import com.freestyle.service.admin.IMonitorService;

@Service
public class MonitorServiceImpl implements IMonitorService {
	
	private Sigar sigar = new Sigar();

	@Override
	public int getCpuUsed() throws SigarException {
		CpuPerc cpuPerc = sigar.getCpuPerc();
		return (int) (cpuPerc.getCombined() * 100);
	}

	@Override
	public int getMemoryUsed() throws SigarException { 
		Mem mem = sigar.getMem();
		return (int) (mem.getUsed() * 1.0 / mem.getTotal() * 100);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getIfNames() throws SigarException {
		String ifNames[] = sigar.getNetInterfaceList();
		return Arrays.asList(ifNames);
	}
	
	@Override
	public long getNetBytes(String ifname) throws SigarException {
		String[] nis = sigar.getNetInterfaceList();
		long total = 0;
		for(String ni : nis) {
			NetInterfaceStat ifstat = sigar.getNetInterfaceStat(ni); 
			total += ifstat.getSpeed();
		}
		return total;
	}

}
