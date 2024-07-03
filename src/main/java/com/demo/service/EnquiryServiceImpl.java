package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Repository.CourseRepo;
import com.demo.Repository.EnqStatusRepo;
import com.demo.Repository.StudentEnqRepo;
import com.demo.Repository.UserDtlsRepo;
import com.demo.binding.DashboardResponse;
import com.demo.binding.EnquiryForm;
import com.demo.binding.EnquirySearchCriteria;
import com.demo.entity.CourseEntity;
import com.demo.entity.EnqStatusEntity;
import com.demo.entity.StudentEnqEntity;
import com.demo.entity.UserDtlsEntity;

import jakarta.servlet.http.HttpSession;

@Service
public class EnquiryServiceImpl implements EnquiryService {

@Autowired
private UserDtlsRepo userDtlsRepo;

@Autowired	
private CourseRepo courseRepo;

@Autowired	
private EnqStatusRepo statusRepo;
@Autowired	
private StudentEnqRepo enqRepo;
@Autowired
private HttpSession session;
//@Autowired
//private StudentEnqRepo enqRepo;

@Override
public DashboardResponse getDashboardData(Integer userId) {

	    DashboardResponse response = new DashboardResponse();

	    Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);

	    if (findById.isPresent()) {
	        UserDtlsEntity userEntity = findById.get();
	        List<StudentEnqEntity> enquiries = userEntity.getEnquiries();

	        Integer totalCnt = enquiries.size();
	        Integer enrolledCnt = enquiries.stream()
	                .filter(e -> e.getEnqStatus().equals("ENROLLED"))
	                .collect(Collectors.toList()).size();
	        Integer lostCnt = enquiries.stream()
	                .filter(e -> e.getEnqStatus().equals("LOST"))
	                .collect(Collectors.toList()).size();

	        response.setTotalEnquriesCnt(totalCnt);
	        response.setEnrolledCnt(enrolledCnt);
	        response.setLostCnt(lostCnt);
	    } else {
	        // Set default values if user is not found
	        response.setTotalEnquriesCnt(0);
	        response.setEnrolledCnt(0);
	        response.setLostCnt(0);
	    }

	    return response;
	}











//course

	@Override
	public List<String> getCourseName() {
	
		List<CourseEntity> findAll=	courseRepo.findAll();
	
		List<String> names=new ArrayList<>();
	
		for(CourseEntity entity:findAll) {
		
			names.add(entity.getCourseName());
			}
	return names;
	}

	
	
	
	
	
	//status
	
	@Override
	public List<String> getEnqStatus() {
		
		List<EnqStatusEntity> findAll= statusRepo.findAll();
		List<String> statusList =new ArrayList<>();
		for(EnqStatusEntity entity : findAll) {
			statusList.add(entity.getStatusName());
		
		}return statusList;
	}

	
	@Override
	public boolean upsertEnquiry(EnquiryForm form) {
		StudentEnqEntity enqEntity=new  StudentEnqEntity();
		BeanUtils.copyProperties(form, enqEntity);
		
		Integer userId=(Integer) session.getAttribute("userId");
	UserDtlsEntity userEntity=	userDtlsRepo.findById(userId).get();
	enqEntity.setUser(userEntity);	
	enqRepo.save(enqEntity);
		
		return true;
		
	}

	@Override
	public List<EnquiryForm> getEnquires(Integer userId, EnquirySearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnquiryForm getEnquiry(Integer EnqId) {
		// TODO Auto-generated method stub
		return null;
	}

}
