package com.prueba.services;

import java.util.List;

import com.prueba.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente findById(String id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(String id);

}
