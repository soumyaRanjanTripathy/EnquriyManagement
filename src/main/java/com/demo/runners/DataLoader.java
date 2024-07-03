package com.demo.runners;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.demo.Repository.CourseRepo;
import com.demo.Repository.EnqStatusRepo;
import com.demo.entity.CourseEntity;
import com.demo.entity.EnqStatusEntity;

@Component
public class DataLoader  implements ApplicationRunner{
	@Autowired	
	private CourseRepo courseRepo;

	@Autowired	
	private EnqStatusRepo statusRepo; 
	@Override
	public void run(ApplicationArguments args) throws Exception {
		courseRepo.deleteAll();
		CourseEntity c1= new CourseEntity();
		c1.setCourseName("java");
		
		CourseEntity c2= new CourseEntity();
		c2.setCourseName("php");
		
		CourseEntity c3= new CourseEntity();
		c3.setCourseName("sql");
		
		courseRepo.saveAll(Arrays.asList(c1,c2,c3));
		
		statusRepo.deleteAll();
		EnqStatusEntity s1=new EnqStatusEntity();
		s1.setStatusName("New");
		EnqStatusEntity s2=new EnqStatusEntity();
		s2.setStatusName("Enrolled");
		EnqStatusEntity s3=new EnqStatusEntity();
		s3.setStatusName("Lost");
		statusRepo.saveAll(Arrays.asList(s1,s2,s3));
	}

}
