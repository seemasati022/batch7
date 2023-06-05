package com.tejait.batch7.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //makes sure to generate id in sequential order.
    private int id;
    private String fname;
    private String lname;
    private String fullname;
    private int age;
    private String pan;
}
