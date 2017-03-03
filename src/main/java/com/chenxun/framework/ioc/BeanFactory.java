package com.chenxun.framework.ioc;

import java.util.Map;

public interface BeanFactory <T>{
	
	
  T getBean();
  
  void setProperties(Map<String,Object> map);

	
}
