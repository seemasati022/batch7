package com.tejait.batch7.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "location_tbl")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Transient
    String locationName;
    String Latitiude;
    String Longitude;

    @Transient
    MultipartFile multipartFile;
}
