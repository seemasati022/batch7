package com.tejait.batch7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/restTemplateCommunication")
public class CommunicationController {

    //@Autowired
    //RestTemplate restTemplate;
    RestTemplate restTemplate = new RestTemplate();         //both servers has to be up



    @GetMapping("/getName")
    public String getName(){
        String forObject = restTemplate.getForObject("http://localhost:8081/restTemplate/getName", String.class);
        String ourOBject = "Teja IT";
        return ourOBject.concat("   "+forObject);
    }

}
