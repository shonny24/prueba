package com.prueba.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.arbolBinario.Arbol;
import com.prueba.arbolBinario.Nodo;
import com.prueba.entity.Cliente;
import com.prueba.excepciones.AgregarNodoException;
import com.prueba.services.IClienteService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200", "*" })
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	private Arbol arbol = new Arbol();

	@PostConstruct
	public void init() {
		List<Cliente> clientes = clienteService.findAll();

		for (int i = 0; i < clientes.size(); i++) {
			// int indice = arbol.generarLlave();
			Nodo nodoAgregado = arbol.agregarNodo(clientes.get(i).getSerial(), clientes.get(i));
			System.out.println(clientes.get(i));
			System.out.println(nodoAgregado.toString());
		}
	}

	@GetMapping("/clientes")
	public List<Cliente> getAll() {
		arbol.getClientes().clear();
		arbol.findAll(arbol.getRaiz());
		return arbol.getClientes();
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {

		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		//Integer serial = cliente.getSerial();
		try {

			if (arbol.validarLlave(cliente.getSerial())) {
				Nodo nodoAgregado = arbol.agregarNodo(cliente.getSerial(), cliente);
				cliente = nodoAgregado.getCliente();
			}

			clienteNew = clienteService.save(cliente);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NullPointerException e) {
			response.put("mensaje", "El serial no puede estar en blanco");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} catch (AgregarNodoException e1) {
			response.put("mensaje", "Ya existe un cliente con el serial " + cliente.getSerial());
			response.put("error", e1.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	

		response.put("mensaje", "El cliente ha sido creado con éxito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/clientes/{llave}")
	public ResponseEntity<?> buscarSerial(@PathVariable Integer llave) {
		Cliente cliente = new Cliente();
		Map<String, Object> response = new HashMap<>();
		try {

			cliente = arbol.buscarNodo(llave).getCliente();


		} catch (NullPointerException e) {
			response.put("mensaje", "El cliente ".concat(llave.toString().concat(" no existe en la base de datos!")));
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PutMapping("/clientes/{llave}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Integer llave) {

		Cliente clienteUpdated = new Cliente();
		Cliente clienteActual = new Cliente();

		Map<String, Object> response = new HashMap<>();
		

		try {

			clienteActual = arbol.buscarNodo(llave).getCliente();
			

			clienteActual.setNombres(cliente.getNombres());
			clienteActual.setApellidos(cliente.getApellidos());
			clienteActual.setDireccion(cliente.getDireccion());
			clienteActual.setCorreo(cliente.getCorreo());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setTelefonoAlter(cliente.getTelefonoAlter());
			clienteActual.setCelular(cliente.getCelular());
			clienteActual.setCargo(cliente.getCargo());
			clienteActual.setDsCargo(cliente.getDsCargo());
			clienteActual.setCiudad(cliente.getCiudad());
			clienteActual.setDsCiudad(cliente.getDsCiudad());
			clienteActual.setFeNacimiento(cliente.getFeNacimiento());
			clienteActual.setGenero(cliente.getGenero());
			clienteActual.setComunidad(cliente.getComunidad());
			clienteActual.setDsComunidad(cliente.getDsComunidad());
			clienteActual.setEmpresaLaboral(cliente.getEmpresaLaboral());
			clienteActual.setDivision(cliente.getDivision());
			clienteActual.setDsDivision(cliente.getDsDivision());
			clienteActual.setPais(cliente.getPais());
			clienteActual.setHobbies(cliente.getHobbies());

			Nodo nodoAgregado = arbol.updateNodo(clienteActual.getSerial(), clienteActual);
			cliente = nodoAgregado.getCliente();

			clienteUpdated = clienteService.save(cliente);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NullPointerException e1) {
			response.put("mensaje", "El cliente ".concat(llave.toString().concat(" no existe en la base de datos!")));
			response.put("error", e1.getMessage().concat(": ").concat(e1.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El cliente ha sido actualizado con éxito");
		response.put("cliente", clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
