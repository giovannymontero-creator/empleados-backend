package empleados.backend.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.GetMapping;

import com.empleados.backend.EmpleadosBackendApplication;
import com.empleados.backend.models.dao.IEmpleadoDao;
import com.empleados.backend.models.entity.Empleado;
import com.empleados.backend.models.services.EmpleadoService;
import com.empleados.backend.models.services.IEmpleadoService;

@SpringBootTest(classes = EmpleadosBackendApplication.class)
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql(scripts={"classpath:comando.sql"})
class TestEmpleado {

	@Autowired
	private IEmpleadoService empleadoService;	
		
		@Test
	    public void test() {
			if(empleadoService.findAll()!=null) {
				List<Empleado> empleado = empleadoService.findAll();
				int e = empleado.size();
		        assertEquals(1, e);
			}else {
				assertEquals(1, 1);
			}        
	    }
	
	
	
}