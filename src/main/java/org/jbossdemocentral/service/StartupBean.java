package org.jbossdemocentral.service;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TimerService;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.jbossdemocentral.model.Demo;
import org.jbossdemocentral.model.DemoDao;

@Singleton
@Startup
public class StartupBean {

	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Inject
	DemoDao demoDao;
	
	@Resource
	private TimerService timerService;
	
	@Inject
	Cache<String,List<Demo>> cache;
	
	@PostConstruct
	void init() {
		cache.put(DemoRestService.DEMO_LIST_CACHE_ENTRY_KEY, demoDao.getDemos());
	}
	
	@Schedule(hour="*")
	void refreshCache() {
		cache.put(DemoRestService.DEMO_LIST_CACHE_ENTRY_KEY, demoDao.getDemos());
	}

}
