package com.tejait.batch7.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Data     -> above all these are combine available in data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fname;
    private String lname;
    private String fullname;
    private String dept;
    private Integer age;
    private Long salary;
    private String empCode;
}
