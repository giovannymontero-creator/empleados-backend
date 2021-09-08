package com.empleados.backend.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.empleados.backend.models.entity.Empleado;

public interface IEmpleadoDao extends CrudRepository <Empleado, Long>{

}
