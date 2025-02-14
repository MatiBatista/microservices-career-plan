package com.eldar.person_service.repositories;

import com.eldar.person_service.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

   Optional<Customer> findByDniOrEmail(String dni, String email);

}
