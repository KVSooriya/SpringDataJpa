package com.vismee.springrestcrud.rest;

import com.vismee.springrestcrud.customexception.EmployeeNotFoundException;
import com.vismee.springrestcrud.entity.Employee;
import com.vismee.springrestcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController
{
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees()
    {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId)
    {
        Employee employee = employeeService.findById(employeeId);

        if(employee == null)
            throw new EmployeeNotFoundException("Employee not found for the id " +employeeId);

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        /* if they provide id in RequestBody, update the id to 0 in order to add the data
         else the data gets updated for the provided id based on merge method we written on dao code */
         employee.setId(0);
         Employee dbEmployee = employeeService.save(employee);
         return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee)
    {
        Employee emp = employeeService.findById(employee.getId());

        if(emp == null)
            throw new EmployeeNotFoundException("Employee not found for update");

        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)
    {
        Employee employee = employeeService.findById(employeeId);

        if(employee == null)
            throw new EmployeeNotFoundException("Employee not found for the id " +employeeId);

        employeeService.deleteById(employeeId);
        return "Employee deleted for the id " +employeeId;
    }
}
