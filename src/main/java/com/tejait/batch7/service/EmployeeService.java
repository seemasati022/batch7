package com.tejait.batch7.service;

import com.tejait.batch7.model.Employee;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    //by default -> all methods are public abstract, we can write default & static methods

    Employee saveEmployee(Employee emp);

    void deleteEmployee(Integer id);

    Optional<Employee> getEmployeeById(Integer id);

    List<Employee> getAllEmployee();


    List<Employee> getEmpsByFname(String name);

    List<Employee> getEmpsByFAndLname(String f, String l);

    List<Employee> getEmpsBetweenSal(Long salary1, Long salary2);

    List<Employee> getEmpsListByGreaterThanAge(Integer age);

    List<Employee> getEmpListNameContains(String s);

    List<Employee> getEmpListBasedOnNameDesc(Integer a);

    List<Employee> getDistinctLnameList(String lname);

    List<Employee> getSalaryIn(List<Long> sal);

    Optional<Employee> getAgeIsNull();

    //pagination
    Page<Employee> getPagination(Integer page, Integer size);

    List<Employee> getLikeData(String lanme);

    List<Employee> getCompleteSearchData(String val);

    List<Employee> searchFilter(String filterType, String empcode);


    Boolean getByID(Integer id);
}
