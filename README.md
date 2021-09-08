# Prueba Empleado

### Aplicacion 

Es una api restful que contiene el crear, actualizar, listar y eliminar un empleado comprendido
con un nombre y la funcion.

#### Funcionamiento en Angular

##### Listar Empleados
Se muestran el listado de empleados, en este momento solo existe uno que se deja inicial para
verificar que funciona correctamente.
![listar](https://user-images.githubusercontent.com/66186685/132532721-6605c75e-7f64-49b0-b5cd-44f82e11a621.png)

##### Crear empleado
Se muestra para crear un empleado, con su nombre y funcion
![Crear](https://user-images.githubusercontent.com/66186685/132532741-b56caaf9-a164-41fe-9e06-37f1fc3cb5af.png)

##### Editar o actualizar empleado
Se muestra para actualizar un empleado, con su nombre y funcion cargados y listos para editar
![editar](https://user-images.githubusercontent.com/66186685/132532752-71439e5b-7cc2-4469-9320-fa227f23f36a.png)

##### Eliminar empleado
Se muestra un botón para eliminar empleado y su respectiva confirmación
![Confirmareliminar](https://user-images.githubusercontent.com/66186685/132532762-d3ecfd2f-ea7a-4a7b-b35e-e2ed059a6f10.png)

## Notas importantes
Lastimosamente no he manejado docker para los proyectos, por lo tanto no se logro persistir la base
 de datos y se debe crear en MySql que es el motor escogido.

## Creación de base de datos
Unicamente se debe crear la base de datos con el nombre db_prueba en cualquier gestor de MySQL por ejemplo Workbench, logearse con usuario root y la contraseña respectiva.

Script: create database db_prueba;

## Clave de usuario root de MySql
Las claves del usuario root se pueden cambiar en la siguiente ubicación del proyecto de Spring Boot
 src/main/resources -> application.properties -> spring.datasource.password
 y se debe colocar la contraseña correspondiente

## Clonar y ejecutar
- Verificar que el servicio de MySQL se encuentre con el estado Running.
- Realizar git clone del repositorio en cualquier carpeta.
- Abrir proyecto con Spring Tools Suite.
- Ejecutar aplicación con Spring Boot app: empleados-backend
- Seguir con las instrucciones del repositorio de angular
