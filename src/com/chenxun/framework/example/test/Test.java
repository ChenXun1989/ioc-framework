package com.chenxun.framework.example.test;

import com.chenxun.framework.example.entity.Student;
import com.chenxun.framework.ioc.ApplicationContext;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {
		ApplicationContext context=new ApplicationContext("com.chenxun.framework.example.entity");
		Student s=(Student) context.getBean("student");
		s.test();

	}

}
