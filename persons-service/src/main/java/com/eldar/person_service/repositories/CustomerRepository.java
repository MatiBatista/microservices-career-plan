package com.eldar.person_service.repositories;

import com.eldar.person_service.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Person, Long> {

   Optional<Person> findByDniOrEmail(String dni, String email);


}
