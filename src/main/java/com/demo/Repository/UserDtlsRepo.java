package com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.UserDtlsEntity;
@Repository
public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity,Integer> {
//public UserDtlsEntity findByEmail(String email);

public UserDtlsEntity findByEmail(String email);
public UserDtlsEntity  findByEmailAndPwd(String email,String pwd);

}
