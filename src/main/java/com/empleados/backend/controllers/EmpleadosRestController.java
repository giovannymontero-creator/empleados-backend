package com.empleados.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.empleados.backend.models.entity.Empleado;
import com.empleados.backend.models.services.IEmpleadoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmpleadosRestController {

	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping("/empleados")
	public List<Empleado> index() {
		return empleadoService.findAll();
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleado = empleadoService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		} 
		
		if (empleado == null) {
			response.put("mensaje", "La empleado ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Empleado> (empleado, HttpStatus.OK);
	}
	
	@PostMapping("/empleados")
	public ResponseEntity<?> create(@RequestBody Empleado empleado) {
		Empleado empleadoNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			empleadoNew = empleadoService.save(empleado);
		} catch(DataIntegrityViolationException e) {
			response.put("mensaje", "El nombre del empleado " + empleado.getName() + " ya existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El empleado ha sido creada con éxito!");
		response.put("cliente", empleadoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/empleados/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Empleado empleado, @PathVariable Long id) {
		Empleado empleadoActual = empleadoService.findById(id);
		Empleado empleadoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if (empleadoActual == null) {
			response.put("mensaje", "Error: no se puede editar, el empleado ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			empleadoActual.setName(empleado.getName());
			empleadoActual.setFuncion(empleado.getFuncion());
			
			empleadoUpdated = empleadoService.save(empleadoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido actualizada con éxito!");
		response.put("cliente", empleadoUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleadoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
