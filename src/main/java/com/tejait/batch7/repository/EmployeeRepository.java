package com.tejait.batch7.repository;

import com.tejait.batch7.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //no methods here
    //we use methods from parent,grandparent,grandparent's parent
    //we can write syntaxed queries as per JPA
    //findBy -> followed by the syntaxed query

    @Query("select e from Employee e")
    List<Employee> getAllEmps();            //custom query using @query


    @Query("select e from Employee e where e.fname=?1")
    List<Employee> findByFirstName(String s);       //jpa cant understand this syntax it cant create query.

    List<Employee> findByFname(String s);

    List<Employee> findByFnameAndLname(String s1, String s2);

    List<Employee> findBySalaryBetween(Long sal, Long sal2);

    List<Employee> findByAgeGreaterThanEqual(Integer age);

    List<Employee> findByFullnameContaining(String name);

    List<Employee> findByAgeOrderByFullnameDesc(Integer age);

    List<Employee> findDistinctByLname(String s1);

    List<Employee> findBySalaryIn(List<Long> sal);

    Optional<Employee> findByAgeIsNull();

    @Query("select e from Employee e where e.lname LIKE %?1%")
    List<Employee> likeData(String input);


    @Query("select e from Employee e where concat(e.fname,e.lname,e.fullname,e.dept,e.age,e.salary,e.empCode) LIKE %?1%")
    List<Employee> searchLikeData(String val);      //any datatype in T, we can take string

    //----searchfilters-----

    public List<Employee> findByEmpCodeStartingWith(String empCode);
    public List<Employee> findByEmpCodeEndingWith(String empCode);
    public List<Employee> findByEmpCodeContains(String empCode);
    public List<Employee> findByEmpCodeNotContains(String empCode);
    public List<Employee> findByEmpCodeEquals(String empCode);
    public List<Employee> findByEmpCodeNot(String empCode);

    //----------------------
}
