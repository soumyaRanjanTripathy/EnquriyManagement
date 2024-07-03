package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.binding.LoginForm;
import com.demo.binding.SignUpForm;
import com.demo.binding.UnlockForm;
import com.demo.service.UserService;

@Controller
public class UserController {
@Autowired
	private UserService userService;
	



	@GetMapping("/signup")
	public String showSignUpForm(Model model) {
    model.addAttribute("user", new SignUpForm());
    return "signup";
	}

@PostMapping("/signup")
public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model) throws Exception {
   
	boolean status = userService.signup(form);
    
	if (status) {
        model.addAttribute("succMsg", "Check your mail");
    } else {
        model.addAttribute("errMsg", "Enter Unique mail Id to register ");
    }
    return "signup";
}

	@GetMapping("/login")
	public String loginpage(Model model) {
		 
		model.addAttribute("loginForm",new LoginForm());
		
		return "login";
		
	}

	@PostMapping("/login")
	public String loginpage( @ModelAttribute("loginForm")LoginForm loginForm,Model model) {
		
		// System.out.println(loginForm);
		
		String  status=userService.login(loginForm);
		
		if(status.contains("success")) {
			
			//display dashbord
			
			return "redirect:/dashboard";
			
		}
		
		model.addAttribute("errMsg",status);
		
		return "login";
		
	}

	

@GetMapping("/unlock")
public String unlockAccount(@RequestParam String email,Model model) {
 
	UnlockForm unlockFormObj=new UnlockForm();
 
	unlockFormObj.setEmail(email);
  //set to UI
	model.addAttribute("unlock", unlockFormObj);
    
	return "unlock";
}



@PostMapping("/unlock")
public String unlockUserAccount(@ModelAttribute("unlock")UnlockForm unlock ,Model model) {
	
	System.out.println(unlock);
if(	unlock.getNewPwd().equals(unlock.getConfirmPwd()))
{
boolean status=	userService.unlockAccount(unlock);
	
	if(status) {
	model.addAttribute("succMsg","Your Account unlocked successfully ");		
	}else {
	model.addAttribute("errMsg","Given temporary pwd is incorrect,check your email ");		
	
	}
}
else {
		model.addAttribute("errMsg","NEW PWD AND Confirm PWD should Be same");		
	}
	
	return "unlock";
	
}

@GetMapping("/forgot")
public String forgotPwdPage() {
	return "forgotPwd";
	
}
@PostMapping("/forgotPwd")
public String forgotPwd(@RequestParam("email") String email,Model model) throws Exception {
	System.out.println(email);
boolean status=userService.forgotPwd(email);
	if(status) {
		model.addAttribute("succMsg","Pwd send to your email");			
	}
	else {
		model.addAttribute("errMsg","Pwd not  send to your email");			
	}

return "forgotPwd";
	
}
}
