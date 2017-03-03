package com.chenxun.framework.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ApplicationContext {
	
	private  String backPackage;
	
	private CompentScan compentScan=new CompentScan();
	
	
	
	private final Map<Class, BeanFactory>  beanFactorys=new HashMap<>();
	
	private final Map<String,Object> map=new HashMap<>();
	
	public ApplicationContext (String baskPackage) throws ClassNotFoundException{
		this.backPackage=baskPackage;
		init();
	}
	
	public Object getBean(String beanName){
		return map.get(beanName);
	}
	
	private void init() throws ClassNotFoundException{
		compentScan.scan(backPackage);
		for(String clasName:compentScan.getList()){
			Class cls=Class.forName(clasName);
			Compent c=(Compent) cls.getAnnotation(Compent.class);
			if(c!=null){
				String key=c.value();			
				BeanFactory bf=new SimpleBeanFactory(cls);
				beanFactorys.put(cls, bf);	
				map.put(key, bf.getBean());
			}
		}
		for(Entry<Class, BeanFactory> entry :beanFactorys.entrySet()){
			entry.getValue().setProperties(map);
		}
		
	}
	 

}
