package com.tejait.batch7.service.seriveImpl;

import com.tejait.batch7.model.Person;
import com.tejait.batch7.repository.PersonRepository;
import com.tejait.batch7.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepo;

    @Override
    public Person savePerson(Person person) {
        String fname = person.getFname();
        String lname = person.getLname();
        person.setFullname(fname.trim().toUpperCase() + " " + lname.trim().toUpperCase());

  /*      String pan = person.getPan();
        if (pan.trim().length() <7){
            System.out.println("enter pan correctly");
        }*/
        personRepo.save(person);
        return person;
    }

    @Override
    public Person updatePerson(Person person){
        return personRepo.save(person);
    }

    public void deletePerson(Integer id){
        personRepo.deleteById(id);
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> personList = personRepo.findAll();
        return personList;
    }

    @Override
    public Optional<Person> getPersonById(Integer id) {
        return personRepo.findById(id);
    }




}
