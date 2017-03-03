package com.chenxun.framework.ioc;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleBeanFactory implements BeanFactory {

	private Object obj;
	
	private final Class cls;
	
	public SimpleBeanFactory(Class cls){
		    this.cls=cls;
		try {		
			this.obj=cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public Object getBean() {
		return obj;
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
                            	 m.invoke(obj, map.get(a.value()));
                             }
	                    }
	                 }
	         } catch (Exception e) {
	         
	         } 
			
		
		
	}




	

}
