package org.jbossdemocentral.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.jbossdemocentral.model.Demo;
import org.jbossdemocentral.model.DemoDao;

@Singleton
@Startup
public class StartupBean {

	@Inject
	DemoDao demoDao;
	
	@Inject
	Cache<String,List<Demo>> cache;
	
	@PostConstruct
	void init() {
		cache.put(DemoRestService.DEMO_LIST_CACHE_ENTRY_KEY, demoDao.getDemos());
	}

}
