package com.prueba.arbolBinario;

import java.util.ArrayList;
import java.util.List;

import com.prueba.entity.Cliente;
import com.prueba.excepciones.AgregarNodoException;

import lombok.Data;

@Data
public class Arbol {

	private Nodo raiz;
	
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public Arbol() {
		this.raiz = null;
	}

	// Método para insertar un nodo en el arbol
	public Nodo agregarNodo(Integer indice, Cliente cliente) {
		Nodo nuevo = new Nodo(indice, cliente);
		// Si raiz esta vacio, nuevo es igual a raiz
		if (raiz == null) {
			System.out.println("ENTRO RAIZ");
			raiz = nuevo;
			return nuevo;
		} else {
			// Nodo auxiliar que apunta a raiz
			Nodo auxiliar = raiz;
			// Nodo padre es null
			Nodo padre;
			while (true) {
				// Padre apunta a la raiz
				padre = auxiliar;
				// Si el dato es menor
				if (indice < auxiliar.getLlave()) {
					auxiliar = auxiliar.getIzquierda();
					if (auxiliar == null) {
						padre.setIzquierda(nuevo);
						System.out.println("ENTRO izquierda");
						return nuevo;
					}
				} else {
					auxiliar = auxiliar.getDerecha();
					if (auxiliar == null) {
						padre.setDerecha(nuevo);
						System.out.println("ENTRO derecha");
						return nuevo;
					}
				}
			}

		}

	}

	// Método para saber cual el árbol está vacio
	public boolean estaVacio() {
		return raiz == null;
	}
	
	public boolean validarLlave(Integer llave) throws AgregarNodoException{
		if (buscarNodo(llave) != null) {
			throw new AgregarNodoException("Ya existe un cliente con el serial " + llave);
		} else {
			return true;
		}

	}

	// Método para buscar un Nodo en el Árbol
	public Nodo buscarNodo(Integer llave) {
		Nodo aux = raiz;

		while (aux.getLlave() != llave) {
			if (llave < aux.getLlave()) {
				aux = aux.getIzquierda();
			} else {
				aux = aux.getDerecha();
			}
			if (aux == null) {
				return null;
			}
		}
		return aux;
	}

	public Integer generarLlave() {
		Integer llave = null;
		Nodo busqueda = null;
		if (estaVacio()) {
			llave = (int) Math.floor(Math.random() * 200 + 1);
		} else {
			do {
				llave = (int) Math.floor(Math.random() * 200 + 1);
				busqueda = buscarNodo(llave);
			} while (busqueda != null);
		}
		return llave;
	}

	
    //Método para recorrer el árbol InOrden
    public List<Cliente> findAll(Nodo raiz){
        if(raiz!=null){
        	findAll(raiz.getIzquierda());
			System.out.print(raiz.getLlave() + ", ");
			clientes.add(raiz.getCliente());
            findAll(raiz.getDerecha());
        }
        return clientes;
    }
    
	// Método para buscar un Nodo en el Árbol
	public Nodo updateNodo(Integer llave, Cliente cliente) {
		Nodo aux = raiz;
		

		while (aux.getLlave() != llave) {
			if (llave < aux.getLlave()) {
				aux = aux.getIzquierda();
				if(cliente.getSerial() == aux.getCliente().getSerial()) {
					aux.setCliente(cliente);
					System.out.println(aux);
				}
				
			} else {
				aux = aux.getDerecha();
				if(cliente.getSerial() == aux.getCliente().getSerial()) {
					aux.setCliente(cliente);
					System.out.println(aux);
				}
			}
		}
		return aux;
	}

}
