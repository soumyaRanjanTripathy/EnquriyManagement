package com.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ALT_COURSES")
@Data
@Setter
@Getter
public class CourseEntity {
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer courseId;
private String courseName;


}
