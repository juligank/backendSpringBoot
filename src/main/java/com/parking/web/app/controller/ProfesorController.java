package com.parking.web.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.web.app.model.Profesor;
import com.parking.web.app.repository.ProfesorRepository;


//@CrossOrigin(origins = "http://localhost:4200") //http://localhost:4200" -Angular o "*" cualquier origen
@RestController
@RequestMapping("/api")
public class ProfesorController {

	@Autowired
	ProfesorRepository profesorRepository;

	@GetMapping("/profesores")
	public ResponseEntity<List<Profesor>> getAllProfesores(@RequestParam(required = false) String cedula) {
		try {

			List<Profesor> profesores = new ArrayList<Profesor>();

			if (cedula == null)
				profesorRepository.findAll().forEach(profesores::add);
			else
				profesorRepository.findByCedula(cedula).forEach(profesores::add);

			if (profesores.isEmpty()) {
				return new ResponseEntity<>(profesores, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(profesores, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/profesores")
	public ResponseEntity<Profesor> createProfesor(@RequestBody Profesor profesor) {

		try {
			Profesor _profesor = profesorRepository.save(new Profesor(profesor.getApellido(), profesor.getCedula(), profesor.getMateria(),profesor.getNombre()));
			return new ResponseEntity<>(_profesor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
