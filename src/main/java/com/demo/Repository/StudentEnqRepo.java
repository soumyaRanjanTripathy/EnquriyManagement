package com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.StudentEnqEntity;
@Repository
public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity,Integer> {

}
