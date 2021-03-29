package com.prueba.arbolBinario;

import com.prueba.entity.Cliente;
import com.prueba.excepciones.AgregarNodoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ArbolTest {


    private Arbol arbol = new Arbol();

    @Test
    void agregarNodo() {
        Integer indice = 99;
        Cliente cliente = new Cliente();
        cliente.setSerial(indice);
        cliente.setNombres("Prueba Prueba");
        cliente.setApellidos("Prueba prueba");
        cliente.setDireccion("calle 13");
        cliente.setCorreo("prueba@prueba.com");
        cliente.setTelefono("3165421613");

        Nodo resultado = arbol.agregarNodo(indice, cliente);


        Assertions.assertEquals(99,resultado.getLlave());
        Assertions.assertEquals(99,resultado.getCliente().getSerial());

    }

    @Test
    void estaVacio() {
        Boolean resultado = arbol.estaVacio();

        Assertions.assertTrue(resultado);
    }



    @Test()
    void validarLlave() throws AgregarNodoException {
        Integer indice = 99;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            arbol.validarLlave(indice);
        });


    }

    @Test()
    void validarLlave2() throws AgregarNodoException {
        Integer indice = 99;
        Cliente cliente = new Cliente();
        cliente.setSerial(indice);
        cliente.setNombres("Prueba Prueba");
        cliente.setApellidos("Prueba prueba");
        cliente.setDireccion("calle 13");
        cliente.setCorreo("prueba@prueba.com");
        cliente.setTelefono("3165421613");

        arbol.agregarNodo(indice, cliente);


        Exception exception = assertThrows(AgregarNodoException.class, () -> {
            arbol.validarLlave(indice);
        });
    }

    @Test
    void buscarNodo() {
        Integer indice = 99;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            arbol.buscarNodo(indice);
        });

    }

    @Test
    void buscarNodo2() {
        Integer indice = 99;
        Cliente cliente = new Cliente();
        cliente.setSerial(indice);
        cliente.setNombres("Prueba Prueba");
        cliente.setApellidos("Prueba prueba");
        cliente.setDireccion("calle 13");
        cliente.setCorreo("prueba@prueba.com");
        cliente.setTelefono("3165421613");

        arbol.agregarNodo(indice, cliente);

        Nodo resultado = arbol.buscarNodo(indice);

        Assertions.assertEquals(99,resultado.getLlave());
        Assertions.assertEquals(99,resultado.getCliente().getSerial());

    }


    @Test
    void updateNodo() {

        Integer indice = 99;
        Cliente cliente = new Cliente();
        cliente.setSerial(indice);
        cliente.setNombres("Prueba Prueba");
        cliente.setApellidos("Prueba Prueba");

        arbol.agregarNodo(indice, cliente);

        cliente.setNombres("Prueba2 Prueba2");
        cliente.setApellidos("Prueba2 Prueba2");

        Nodo resultado = arbol.updateNodo(indice, cliente);


        Assertions.assertEquals("Prueba2 Prueba2",resultado.getCliente().getNombres());
        Assertions.assertEquals("Prueba2 Prueba2",resultado.getCliente().getApellidos());

    }
}