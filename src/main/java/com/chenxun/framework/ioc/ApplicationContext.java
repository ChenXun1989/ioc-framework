package com.chenxun.framework.ioc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ApplicationContext {
	
	private  String backPackage;
	
	private CompentScan compentScan=new CompentScan();
	
	
	
	private final Map<Class, BeanFactory>  beanFactorys=new HashMap<>();
	
	private final Map<String,Object> map=new HashMap<>();
	
	private final List<Object> aspectList=new ArrayList<>();
	
	public ApplicationContext (String baskPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		this.backPackage=baskPackage;
		init();
	}
	
	public Object getBean(String beanName){
		return map.get(beanName);
	}
	
	private void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		compentScan.scan(backPackage);
		for(String clasName:compentScan.getList()){
			Class cls=Class.forName(clasName);
			Aspect a=(Aspect) cls.getAnnotation(Aspect.class);
			if(a!=null){
				aspectList.add(cls.newInstance());
			}
		}
		
		
		for(String clasName:compentScan.getList()){
			Class cls=Class.forName(clasName);	
			Compent c=(Compent) cls.getAnnotation(Compent.class);
			if(c!=null){
				String key=c.value();		
				// 判断是否有切面
				boolean flag=false;
				for(Object obj:aspectList){
					Class a=obj.getClass();
					Method[] methods=a.getMethods();
					for(Method m :methods){
						Before b=m.getAnnotation(Before.class);
					    if(b!=null&&cls.getName().startsWith(b.value())){
					    	flag=true;
					    	break;
					    }
					}
				}
				BeanFactory bf=null;
				if(flag){
					bf=new ProxyBeanFactory(cls,aspectList);
				}else{
					bf=new SimpleBeanFactory(cls);
				}
				
				 
				beanFactorys.put(cls, bf);	
				map.put(key, bf.getBean());
			}
		}
		for(Entry<Class, BeanFactory> entry :beanFactorys.entrySet()){
			entry.getValue().setProperties(map);
		}
		
	}
	 

}
