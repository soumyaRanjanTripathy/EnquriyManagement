package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.binding.DashboardResponse;
import com.demo.binding.EnquiryForm;
import com.demo.binding.EnquirySearchCriteria;
@Service
public interface EnquiryService {
	public List<String> getCourseName();
	public List<String> getEnqStatus();

	
	
	//daasboard
	public  DashboardResponse getDashboardData(Integer userId);
	//add

	//search
	public List<EnquiryForm> getEnquires(Integer userId,EnquirySearchCriteria criteria);
	//update

	public EnquiryForm getEnquiry(Integer  EnqId);
	public boolean upsertEnquiry(EnquiryForm formObj);
}
