package com.tejait.batch7.service;

import com.tejait.batch7.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person savePerson(Person person);
    Person updatePerson(Person person);

    void deletePerson(Integer id);

    List<Person> getAllPersons();

    Optional<Person> getPersonById(Integer id);
}
