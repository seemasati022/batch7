package com.tejait.batch7.detoDesignPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personcard")
public class PersonDetailsController {

    @Autowired
    PersonDetailsService service;

    @PostMapping("savePerson")
    public ResponseEntity<PersonDetails> savePersondetails(@RequestBody PersonDetails pd){
        return new ResponseEntity<>(service.savePerson(pd), HttpStatus.OK);
    }

    @GetMapping("getAllDtos")
    public ResponseEntity<List<PersonCardDetailsDTO>> getAllPersonDto(){
        return new ResponseEntity<>(service.getAllPersonDto(new PersonDetails()),HttpStatus.OK);
    }
}
