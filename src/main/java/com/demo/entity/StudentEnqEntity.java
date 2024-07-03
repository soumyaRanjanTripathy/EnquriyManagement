package com.demo.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity

@Table(name="AIT_STUDENT_ENQURIES")
@Data
@Setter
@Getter
public class StudentEnqEntity {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer enqId;
 private String  studentName;
 private Long studentPhone;
 private String classMode;
 private String courseName;
 private String enqStatus;
 @CreationTimestamp
 private LocalDate dateCreated;
 @UpdateTimestamp 
 private LocalDate lastUpdated;
 @ManyToOne
 @JoinColumn(name="user_id")
private UserDtlsEntity user; 
 
 
}
