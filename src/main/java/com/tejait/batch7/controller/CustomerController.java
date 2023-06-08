package com.tejait.batch7.controller;

import com.tejait.batch7.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    @Qualifier("business")
    private CustomerService customerService;

    @GetMapping("/cust")
    public String getCust(){
        return customerService.getCustomerType();
    }


}
