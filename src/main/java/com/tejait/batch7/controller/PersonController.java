package com.tejait.batch7.controller;

import com.tejait.batch7.model.Person;
import com.tejait.batch7.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "savePerson", method = RequestMethod.POST)
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        Person savedPersonBody = personService.savePerson(person);
        return new ResponseEntity<>(savedPersonBody, HttpStatus.OK);
    }

    @RequestMapping(value = "updatePerson", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    @RequestMapping(value = "deletePerson/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePerson(@PathVariable Integer id) {
        personService.deletePerson(id);
        return new ResponseEntity<>("deleted person", HttpStatus.OK);
    }


    @RequestMapping(value = "getAllPerson", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPerson() {
        List<Person> allPersons = personService.getAllPersons();
        return new ResponseEntity<>(allPersons, HttpStatus.OK);
    }


    @RequestMapping(value = "getPersonById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Person>> getPersonById(@PathVariable Integer id) {
        Optional<Person> personById = personService.getPersonById(id);
        return new ResponseEntity<>(personById, HttpStatus.OK);
    }
}
