package com.prueba;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prueba.entity.Cliente;


@SpringBootApplication
public class PruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}
	
//	public void cargarArbolBinario() {
//		List<Cliente> clientes = clienteService.findAll();
//		
//		for (int i = 0; i < clientes.size(); i++) {
//			System.out.println(clientes.get(i));
//		}
//	}
	

	

}
