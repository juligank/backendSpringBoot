package com.parking.web.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.parking.web.app.model.Profesor;


public interface ProfesorRepository extends JpaRepository<Profesor, Long>{

	List<Profesor>findByMateria(String materia);
	List<Profesor>findByCedula(String cedula);
}
