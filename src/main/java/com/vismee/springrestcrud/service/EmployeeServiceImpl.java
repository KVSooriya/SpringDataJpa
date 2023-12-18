package com.vismee.springrestcrud.service;

import com.vismee.springrestcrud.customexception.EmployeeNotFoundException;
import com.vismee.springrestcrud.dao.EmployeeRepository;
import com.vismee.springrestcrud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /* Basic crud operations like findAll(), findById(), save(), deleteById() are available for free
       without any coding in DAO - just by extending JpaRepository and plug in entity type and primary key
       type
     */

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {

        /*
        findById(id) make use of Optional as return type which is a feature introduced by JDK 8 to
        check for null value and assign accordingly
        */

        Optional<Employee> result = employeeRepository.findById(id);
        Employee dbEmployee = null;

        if(result.isPresent())
            dbEmployee = result.get();
        else
            throw new EmployeeNotFoundException("Employee not found for the id " +id);

        return dbEmployee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
