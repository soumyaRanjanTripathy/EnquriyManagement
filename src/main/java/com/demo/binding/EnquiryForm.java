package com.demo.binding;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter //DashboardData 
@Getter
public class EnquiryForm {

	private String studentName;
	private Long studentPhno;
	private String classMode;
	private String courseName;
	private String enqStatus;
	
	
	
	
}
