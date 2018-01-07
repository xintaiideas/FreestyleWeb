package com.freestyle.service.admin;

import java.util.List;

import org.hyperic.sigar.SigarException;

public interface IMonitorService {

	public int getCpuUsed() throws SigarException;
	
	public int getMemoryUsed() throws SigarException;

	long getNetBytes(String ifname) throws SigarException;

	List<String> getIfNames() throws SigarException;

	
}
