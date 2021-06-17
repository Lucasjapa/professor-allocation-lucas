package com.project.professorallocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professorallocation.entity.Departament;

@Repository
public interface DepartamentRepository extends JpaRepository<Departament, Long> {

}
