package com.chenxun.framework.ioc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CompentScan {
	
   private final List<String > list=new ArrayList<>();	 
	
	public void scan(String packageName){
	  String parent=	System.getProperty("user.dir");
	  parent=parent+"/src/main/java";
	  System.out.println(parent);
	  String child=packageName.replaceAll("\\.", "/");
		File f=new File(parent ,child);
		 for(File c:f.listFiles()){
			if(c.getName().endsWith(".java")){
				list.add(packageName+"."+c.getName().substring(0,c.getName().lastIndexOf(".")));
			}
		 }
	
	};
	
	public List<String > getList(){
		return list;
	}

}
