package com.chenxun.framework.ioc;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

public class ProxyBeanFactory implements BeanFactory{

	private Object proxyObject;
	
	private final Class cls;
	
	private Object object;
	
	private List<Object> list;
	
	public ProxyBeanFactory(Class cls,List<Object> aspectList){
		this.cls=cls;
		try {
			object=cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		proxyObject= Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				for(int i=0;i<aspectList.size();i++){
					Object aspectObj=aspectList.get(i);
					Class a=aspectObj.getClass();
					Method[] methods=a.getMethods();
					for(Method m :methods){
						Before b=m.getAnnotation(Before.class);
					    if(b!=null&&cls.getName().startsWith(b.value())){
					    	//切面
					    	m.invoke(aspectObj, method,args);
					    	
					    }
					}
				}
			
				
				
				return method.invoke(object, args);
			}
		});
		
	}
	
	@Override
	public Object getBean() {
		
		return proxyObject;
	}

	@Override
	public void setProperties(Map map) {
		 PropertyDescriptor[] props = null;
         try {
             props = Introspector.getBeanInfo(cls, Object.class).getPropertyDescriptors();             
             if (props != null) {
                 for (int i = 0; i < props.length; i++) {
                     Method m= props[i].getWriteMethod();
                      Autowired a=m.getAnnotation(Autowired.class);
                         if(a!=null){
                        	 m.invoke(object, map.get(a.value()));
                         }
                    }
                 }
         } catch (Exception e) {
         
         } 
		
	}





}
