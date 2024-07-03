package com.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Repository.UserDtlsRepo;
import com.demo.Utils.EmailUtils;
import com.demo.Utils.PwdUtils;
import com.demo.binding.LoginForm;
import com.demo.binding.SignUpForm;
import com.demo.binding.UnlockForm;
import com.demo.entity.UserDtlsEntity;

import jakarta.servlet.http.HttpSession;

@Service


public class UserServiceImpl implements UserService {
 

    @Autowired
    private UserDtlsRepo userDtlsRepo;

    @Autowired
   
    private EmailUtils emailUtils;
    @Autowired
    private HttpSession  session;
    
    public boolean signup(SignUpForm form) throws Exception {
        
    	UserDtlsEntity user= userDtlsRepo.findByEmail(form.getEmail());
    	if(user!=null) {
    		return false;
    	}
    	
    	UserDtlsEntity entity = new UserDtlsEntity();
        BeanUtils.copyProperties(form, entity);
        String tempPwd = PwdUtils.generateRandomPwd();
        entity.setPwd(tempPwd);
        entity.setAccStatus("LOCKED");
        userDtlsRepo.save(entity);

        String to = form.getEmail();
        String subject = "Unlock your account";
        StringBuilder body = new StringBuilder();
        body.append("<h1>Below is the temporary password to unlock your account</h1>");
        body.append("Temporary password: ").append(tempPwd);
        body.append("<br/>");
      
        body.append("<a href=\"http://localhost:1111/unlock?email="+ to+"\">Click here to unlock</a>");
        
        emailUtils.sendEmail(to, subject, body.toString());

        return true;
    }

//login
	@Override
	public String login(LoginForm form) {
		//check given email id and pswd

		UserDtlsEntity entity=userDtlsRepo.findByEmailAndPwd( form.getEmail(),form.getPwd());
		System.out.println(entity);
		if(entity== null) {
			return "Invalid Credentials";
		}
		
		if(entity.getAccStatus().equals("LOCKED")) {
			return "Your Account Locked";
			 
		}
		//create Session and store user data in session
		session.setAttribute("userId", entity.getUserId());
		
		return "success";
	}
	
	
	
private UserDtlsEntity findByEmailAndPwd(String email, String pwd) {

	return null;
}

//unlock formed data 	
	

@Override
	public boolean unlockAccount(UnlockForm form) {
	
		UserDtlsEntity entity=	userDtlsRepo.findByEmail(form.getEmail());
	
	if(entity.getPwd().equals(form.getTempPwd())) {
		entity.setPwd(form.getNewPwd());
		entity.setAccStatus("Unlocked");
		userDtlsRepo.save(entity);
		return true;
	}else {
	return false;
	}
	}
	
	//forgot
	

@Override
	public boolean forgotPwd(String email) throws Exception {
		
		//check record present in db with given email
		
	UserDtlsEntity entity=userDtlsRepo.findByEmail(email);
		//if record not available  send error msg
		
	if(entity==null) {
			//return "invalid Email id "; 
			return false;
		}
		
	//if record available send pwd to email and send sucess message 
		
	String Subject="recover Password";
		
	String body="Your Pwd::"+entity.getPwd();
		
	emailUtils.sendEmail(email, Subject, body);
		
	//return "password send to your mail";
	
	return true;
	}

}
