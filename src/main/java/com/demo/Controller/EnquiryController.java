package com.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.binding.DashboardResponse;
import com.demo.binding.EnquiryForm;
import com.demo.service.EnquiryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	
	@Autowired
	private EnquiryService enquiryService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/logout")
	public String logout()
	{
		 session.invalidate();
		return "index";
		
	}
	//dashboard
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model, HttpSession session) {
	    Integer userId = (Integer) session.getAttribute("userId");
	    if (userId != null) {
	        DashboardResponse dashboardData = enquiryService.getDashboardData(userId);
	        model.addAttribute("dashboardData", dashboardData);
	    } else {
	        model.addAttribute("dashboardData", new DashboardResponse()); // Add an empty object to prevent null reference
	    }
	    return "dashboard";
	}

	
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
	
		//getcourses for dropdown 
		List<String> courses=enquiryService.getCourseName();
		//get status for dropdown
		List<String> enqStatuses=enquiryService.getEnqStatus();
		//create binding class object 
		EnquiryForm formObj=new EnquiryForm();
		
		//set data in model object
		model.addAttribute("courseNames",courses);
		
		model.addAttribute("StatusNames",enqStatuses);
		
		model.addAttribute("formObj",formObj);
		
		return "addEnquiry";
	
	}
	
	
	public String addEnquiry( ) {
		return null;
		
	}
	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj")EnquiryForm formObj ,Model model) {
		System.out.println(formObj);
		//Save the data 
		boolean status= enquiryService.upsertEnquiry(formObj);
		if(status) {
			
		model.addAttribute("succMsg","Enquiry Added");	
		}else {
			model.addAttribute("errMsg","Enquiry Added fail");		
		}
		
		return "addEnquiry";
		
	}
	}
	

/*@GetMapping("/addEnquiry")
	public String showEnquiryForm(Model model) {
	    model.addAttribute("formObj", new EnquiryForm());
	    model.addAttribute("StatusNames", Arrays.asList("New", "In Progress", "Closed"));
	    model.addAttribute("courseNames", Arrays.asList("Course 1", "Course 2", "Course 3"));
	    return "addEnquiry";
	}
	@GetMapping("/enquires")
	public String ViewEnquaryPage() {
	return "viewEnquiry";
	
	}
}
*/