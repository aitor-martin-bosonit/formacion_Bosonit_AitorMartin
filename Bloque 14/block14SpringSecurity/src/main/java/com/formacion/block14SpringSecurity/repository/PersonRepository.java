package com.formacion.block14SpringSecurity.repository;

import com.formacion.block14SpringSecurity.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String user);
}
