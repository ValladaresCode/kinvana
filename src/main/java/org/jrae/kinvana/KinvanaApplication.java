package org.jrae.kinvana;

import lombok.Builder;
import org.jrae.kinvana.dominio.service.IClienteService;
import org.jrae.kinvana.persistence.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class KinvanaApplication implements CommandLineRunner {

	//Inyeccion de dependencia
	@Autowired
	private IClienteService clienteService;
	//Crear objeto(Herramienta) logger para interactuar con la consola
	private static final Logger logger= LoggerFactory.getLogger(KinvanaApplication.class);//org.lf4g
	//Crear un Objeto String para saltos de linea porwue logger no los maneja
	String sl = System.lineSeparator();//Salto de Linea

	public static void main(String[] args) {
		logger.info("AQUI INICIA NUESTRA APP");
		SpringApplication.run(KinvanaApplication.class, args);
		logger.info("AQUI TERMINA LA APP");
	}

	@Override
	public void run(String... args) throws Exception {
		kinvanaClienteAPP();
	}

	private void kinvanaClienteAPP(){
		logger.info("----------------Aplicacion de Registro de CLIENTES---------------");
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(sl);
		}
	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
				\n ****APP****
				1. Listar todos los clientes.
				2. Buscar cllientes por codigo.
				3. Agregar nuevo Cliente
				4. Modificar Cliente.
				5. Eliminar Cliente.
				6. Salir.
				Elije la opcion deseada\s""");
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1 ->{
				logger.info("Listado de Clientes:");
				List<Cliente> clientes = clienteService.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString()+sl));
			}

			case 2 ->{
				logger.info(sl+"Buscar Sliente por ID"+sl);
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClientePorId(codigo);
				if (cliente != null){
					logger.info("Cliente encontrado"+sl+cliente+sl);

				}else{
					logger.info("Cliente no encontrado"+sl+cliente+sl);
				}
			}

			case 3 ->{
				logger.info("Agregar nuevo Cliente");
				logger.info("Ingrese el nombre:");
				var nombre= consola.nextLine();
				logger.info("Ingrese el apellido:");
				var apellido= consola.nextLine();
				logger.info("Ingrese el telefono:");
				var telefono= consola.nextLine();
				logger.info("Ingrese el correo:");
				var correo= consola.nextLine();
				logger.info("Ingrese el genero:");
				logger.info("'masculino','femenino','no'");
				var genero= consola.nextLine();
				logger.info("Ingrese la edad:");
				var edad= Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setTelefono(telefono);
				cliente.setCorreo(correo);
				if (genero.equals("masculino")|| genero.equals("femenino")||genero.equals("no")){
					cliente.setGenero(genero);
				}else{
					logger.info("Genero no valido");
				}
				cliente.setEdad(edad);
				clienteService.guardarCliente(cliente);
				logger.info("Cliente Registrado");
			}

			case 4 ->{
				logger.info(sl+"modificar Cliente"+sl);
				logger.info(sl+"Ingrese el ID del cliente a actualizar"+sl);
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClientePorId(codigo);
				if (cliente != null){
					logger.info("Ingrese el nombre:");
					var nombre= consola.nextLine();
					logger.info("Ingrese el apellido:");
					var apellido= consola.nextLine();
					logger.info("Ingrese el telefono:");
					var telefono= consola.nextLine();
					logger.info("Ingrese el correo:");
					var correo= consola.nextLine();
					logger.info("Ingrese el genero:");
					logger.info("'masculino','femenino','no'");
					var genero= consola.nextLine();
					logger.info("Ingrese la edad:");
					var edad= Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setTelefono(telefono);
					cliente.setCorreo(correo);
					if (genero.equals("masculino")|| genero.equals("femenino")||genero.equals("no")){
						cliente.setGenero(genero);
					}else{
						logger.info("Genero no valido");
					}
					cliente.setEdad(edad);
					clienteService.guardarCliente(cliente);
					logger.info("Cliente Actualizado");
				}else{
					logger.info("Cliente Invalido"+sl+cliente+sl);
				}
			}

			case 5 ->{
				logger.info(sl+"Eliminar Cliente"+sl);
				logger.info("Ingreser ID del cliente a eliminar");
				var codigo = Integer.parseInt(consola.nextLine());
				var cliente = clienteService.buscarClientePorId(codigo);
				if (cliente != null){
					clienteService.eliminarCliebte(cliente);
					logger.info(sl+"Cliente eliminado: "+cliente+sl);
				}else {
					logger.info("Cliente NO encontrado"+cliente);
				}
			}

			case 6 ->{
				logger.info("Hasta pronto"+sl+sl);
				salir = true;
			}

			default -> logger.info("Opcion no valida");
		}
		return salir;
	}
}
