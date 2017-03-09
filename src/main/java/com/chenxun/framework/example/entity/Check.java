package com.chenxun.framework.example.entity;

import java.lang.reflect.Method;

import com.chenxun.framework.ioc.Aspect;
import com.chenxun.framework.ioc.Before;

@Aspect
public class Check {
	
	@Before("com.chenxun.framework.example.entity.Student")
	public void before(Method method,Object[] args){
		if(method.getName().indexOf("testAspect")>-1){
			System.out.println("before ----");
		}

	}

}
