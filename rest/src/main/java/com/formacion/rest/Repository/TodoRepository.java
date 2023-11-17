package com.formacion.rest.Repository;

import com.formacion.rest.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Task, Long> {
}
