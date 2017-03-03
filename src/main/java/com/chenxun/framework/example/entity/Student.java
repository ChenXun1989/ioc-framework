package com.chenxun.framework.example.entity;

import com.chenxun.framework.ioc.Autowired;
import com.chenxun.framework.ioc.Compent;

@Compent("student")
public class Student {
	
	
	private Person person;
	
	public Person getPerson() {
		return person;
	}
	@Autowired("person")
	public void setPerson(Person person) {
		this.person = person;
	}

	public void test(){
		person.test();
		System.out.println("Student ----");
		
	}

}
