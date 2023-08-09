package com.formacion.block7crudvalidationparte1.Repository;

import com.formacion.block7crudvalidationparte1.Entity.Asignatura;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

    List<Asignatura> findAllByStudents_IdStudent(Integer idStudent);

}
