package com.tejait.batch7.service.seriveImpl;

import com.tejait.batch7.model.ApiResponse;
import com.tejait.batch7.model.Employee;
import com.tejait.batch7.model.ResponseBuilder;
import com.tejait.batch7.repository.EmployeeRepository;
import com.tejait.batch7.service.EmployeeService;
import com.tejait.batch7.utils.SearchFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    @Autowired
    ResponseBuilder responseBuilder;

    public Employee saveEmployee(Employee emp){

        //business logic
        String fname = emp.getFname();
        String lname = emp.getLname();
        String fullName = fname.concat(" "+lname);
        emp.setFullname(fullName);
        //logic ends

        //Employee empSave = empRepo.save(emp);
        return empRepo.save(emp);

    }

    @Override
    public void deleteEmployee(Integer id) {
        empRepo.deleteById(id);
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id){
        Optional<Employee> byId = empRepo.findById(id);
        return byId;
    }

    public List<Employee> getAllEmployee(){
       // return empRepo.findAll();
        List<Employee> list = empRepo.getAllEmps();
        for(Employee e:list){
            e.setId(e.getId());
            e.setFname(e.getFname());
            e.setLname(e.getLname());
            e.setFullname(e.getFname());
            e.setAge(e.getAge());
            e.setSalary(e.getSalary());
            e.setEmpCode(e.getEmpCode());
        }
        return list;
    }

    @Override
    public List<Employee> getEmpsByFname(String name) {
        //return empRepo.findByFname(name);
        return empRepo.findByFirstName(name);
    }

    @Override
    public List<Employee> getEmpsByFAndLname(String f, String l){
        return empRepo.findByFnameAndLname(f,l);
    }

    @Override
    public List<Employee> getEmpsBetweenSal(Long sal1, Long sal2){
        return empRepo.findBySalaryBetween(sal1,sal2);
    }

    @Override
    public List<Employee> getEmpsListByGreaterThanAge(Integer age) {
        return empRepo.findByAgeGreaterThanEqual(age);
    }

    @Override
    public List<Employee> getEmpListNameContains(String s) {
        return empRepo.findByFullnameContaining(s);
    }

    @Override
    public List<Employee> getEmpListBasedOnNameDesc(Integer i) {
        return empRepo.findByAgeOrderByFullnameDesc(i);
    }

    @Override
    public List<Employee> getDistinctLnameList(String s){
        return empRepo.findDistinctByLname(s);
    }

    @Override
    public List<Employee> getSalaryIn(List<Long> sal) {
        return empRepo.findBySalaryIn(sal);
    }

    @Override
    public Optional<Employee> getAgeIsNull() {
        return empRepo.findByAgeIsNull();
    }

    @Override
    public Page<Employee> getPagination(Integer pageNo, Integer recSize) {
        Pageable pageable = PageRequest.of(pageNo, recSize);
        return empRepo.findAll(pageable);              //thid findAll() expects pagable obj which contains pagerequest.of(page,size)
        //now page is created and rec count is fixed, so when it enters db, it fetches only given number of records to the page.
    }

    @Override
    public List<Employee> getLikeData(String lanme){
        return empRepo.likeData(lanme);
    }

    @Override
    public List<Employee> getCompleteSearchData(String val){
     return empRepo.searchLikeData(val);
    }

    public List<Employee> searchFilter(String filterType,String empcode){
        List<Employee> list = new ArrayList<>();        //emptylist
        //since frontend doesnt know enumtype, we need to convert to string
        SearchFilters searchFilters = SearchFilters.valueOf(filterType);
        switch (searchFilters){
            case startsWith :
                list = empRepo.findByEmpCodeStartingWith(empcode);
                break;
            case endsWith:
                list = empRepo.findByEmpCodeEndingWith(empcode);
                break;
            case equals:
                list = empRepo.findByEmpCodeEquals(empcode);
                break;
            case notEquals:
                list = empRepo.findByEmpCodeNotContains(empcode);
                break;
            case contains:
                list = empRepo.findByEmpCodeContains(empcode);
                break;
            case notContains:
                list = empRepo.findByEmpCodeNot(empcode);
                break;
        }
        return list;
    }

    @Override
    public Boolean getByID(Integer id){
        return empRepo.existsById(id);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllEmpsApiResp(HttpHeaders headers){
        List<Employee> listData = empRepo.findAll();//.stream().collect(Collectors.toCollection(HashSet::new));
        return responseBuilder.buildResponse(headers,200,"fetching all emps data using apiResponse",listData);
    }

}
