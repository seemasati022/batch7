package com.tejait.batch7.service.seriveImpl;

import com.tejait.batch7.service.CustomerService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("business")
@Primary
public class BusineessCustomerServiceImpl implements CustomerService {
    @Override
    public String getCustomerType() {
        return "Business";
    }
}
