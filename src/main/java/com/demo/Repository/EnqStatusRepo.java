package com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.EnqStatusEntity;

public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity,Integer> {

}
