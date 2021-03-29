package com.prueba.arbolBinario;

import com.prueba.entity.Cliente;

import lombok.Data;

@Data
public class Nodo {

	private Nodo padre;
	private Nodo izquierda;
	private Nodo derecha;
	private Integer llave;
	private Cliente cliente;
	
	
    public Nodo(Integer indice, Cliente cliente) {
        this.llave = indice;
        this.cliente = cliente;
        this.izquierda = null;
        this.derecha = null;
        this.padre = null;
    }

    public Nodo() {
    }
    
    
}
