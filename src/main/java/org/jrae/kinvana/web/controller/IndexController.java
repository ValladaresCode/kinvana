package org.jrae.kinvana.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.jrae.kinvana.dominio.service.IClienteService;
import org.jrae.kinvana.persistence.entity.Cliente;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {

    @Autowired
    IClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.clientes = this.clienteService.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente(){
        this.clienteSeleccionado = new Cliente();

    }

    public void guardarCliente(){
        logger.info("Cliente a guardar: "+this.clienteSeleccionado);
        //Agregar(Insertar)
        if (this.clienteSeleccionado.getCodigoCliente() == null){
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente agregar"));

        }
        //Mpdificar(Update)
        else{
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente Actualizado"));
        }

        //Ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");

        //Actualiar la tabla con AJAX
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje_emergente",
                "formulario-clientes:tabla-clientes");

        //Reset del clienteSeleccionado
        this.clienteSeleccionado = null;
    }

    public void eliminarCliente(){
        //Mostrar en consola
        logger.info("Cliente a eliminar: "+clienteSeleccionado);
        //llamar a nuestro servicio de eliminacion de Cliente
        this.clienteService.eliminarCliebte(clienteSeleccionado);
        //Eliminarlo de la lista clientes
        this.clientes.remove(clienteSeleccionado);
        //limpiar clienteSeleccionado
        this.clienteSeleccionado = null;
        //enviar mnsaje emergente
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("cliente eliminado") );
        //Metodo ajax para actualizar la tabla
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje_emergente",
                "formulario-clientes:tabla-clientes");
    }

}







