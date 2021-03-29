package com.prueba.repository;

import org.springframework.data.repository.CrudRepository;

import com.prueba.entity.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {

}
