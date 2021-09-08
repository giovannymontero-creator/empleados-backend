package com.empleados.backend.models.services;

import java.util.List;

import com.empleados.backend.models.entity.Empleado;

public interface IEmpleadoService {

	public List<Empleado> findAll();
	
	public Empleado findById(Long id);
	
	public Empleado save(Empleado empleado);
	
	public void delete(Long id);
	
}
