package com.chenxun.framework.example.test;

import com.chenxun.framework.example.entity.Student;
import com.chenxun.framework.example.entity.TestAspect;
import com.chenxun.framework.ioc.ApplicationContext;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ApplicationContext context=new ApplicationContext("com.chenxun.framework.example.entity");
		TestAspect s=(TestAspect) context.getBean("student");
		s.test();
		System.out.println("---------------------");	
		s.testAspect();

	}

}
