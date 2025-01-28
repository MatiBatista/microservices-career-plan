package com.eldar.person_service.repositories;

import com.eldar.person_service.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByDniOrEmailOrUsername(String dni, String email, String username);

    Optional<Employee> findByUsername(String username);

}
