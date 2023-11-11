package com.tejait.batch7.detoDesignPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDetailsService {
    @Autowired
    PersonDetailsRepository repository;

    public PersonDetails savePerson(PersonDetails personDetails){
        return repository.save(personDetails);
    }

    public List<PersonCardDetailsDTO> getAllPersonDto(PersonDetails details){
        List<PersonDetails> personEntityList = repository.findAll();            //this returns 3 entity objects
    // passing the entities to stream-> map. in map, calling converttodto method and returns dto objects and collecting them to list
        return personEntityList.stream().map(PersonDetailsService::converTEntityToDto).collect(Collectors.toList());
    }

    public static PersonCardDetailsDTO converTEntityToDto(PersonDetails personDetails){
        PersonCardDetailsDTO dto = new PersonCardDetailsDTO();
        dto.setAge(personDetails.getAge());
        dto.setName(personDetails.getName());
        dto.setCardNumber(personDetails.getCardDetails().getCardNumber());
        dto.setCardHolder(personDetails.getCardDetails().getCardHolder());
        return dto;
    }
}
