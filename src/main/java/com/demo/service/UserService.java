package com.demo.service;

import org.springframework.stereotype.Service;

import com.demo.binding.LoginForm;
import com.demo.binding.SignUpForm;
import com.demo.binding.UnlockForm;
@Service
public interface UserService {

	public String login(LoginForm form);
	public boolean signup(SignUpForm form)throws Exception  ;
	public boolean unlockAccount(UnlockForm form);
	public  boolean forgotPwd(String email)throws Exception;
	
	
	
}
