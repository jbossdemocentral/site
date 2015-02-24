package org.jbossdemocentral.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.infinispan.Cache;
import org.jbossdemocentral.model.Demo;
import org.jbossdemocentral.model.DemoDao;

@Path("/")
@Stateless
public class DemoRestService {
	
	public final static String DEMO_LIST_CACHE_ENTRY_KEY="org.jbossdemocentral.demolist.cachekey";

	@Inject
	DemoDao demoDao;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	Cache<String,List<Demo>> cache;
	
	@GET
    @Path("demos")
    @Produces({ "application/json" })
    public List<Demo> getTasks() {
        return cache.get(DEMO_LIST_CACHE_ENTRY_KEY);
    }
	
	@GET
    @Path("demosreload")
    @Produces({ "application/json" })
    public List<Demo> reloadTasks() {
		List<Demo> demos = demoDao.getDemos();
		if(demos!=null && demos.size()>0) {
			cache.put(DEMO_LIST_CACHE_ENTRY_KEY, demos);
		}
        return demos;
    }
	
	@GET
    @Path("demos/{category}")
    @Produces({ "application/json" })
    public List<Demo> getDemosByCategory(@PathParam("category") String requestCategory) {
		if(requestCategory==null) {
			return null;
		}
		List<Demo> allDemos = cache.get(DEMO_LIST_CACHE_ENTRY_KEY);
		List<Demo> demos = new ArrayList<Demo>();
		for (Demo demo : allDemos) {
			//Check if config has categories
			List<String> categories = demo.getCategories();
			if(categories!=null && categories.size()>0) {
				for (String category : categories) {
					if(requestCategory.equals(category)) {
						demos.add(demo);
					}
				}
			} else if(requestCategory.equals(demo.getCategory())) {
				demos.add(demo);
			}
		}
		return demos;
    }
}
