package com.vismee.springrestcrud.dao;


import com.vismee.springrestcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/* Spring Data JPA - Minimizes the boilerplate code for DAO by extending JpaRepository and plug in
entity type and primary key type and the basic crud operations will be available for free */
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{
    // No need to write any code .. crud operations will be available for free for the Employee entity
}
