package org.jrae.kinvana.dominio.service;

import org.jrae.kinvana.persistence.entity.Cliente;
import java.util.List;

public interface IClienteService {
    //firmas de metodos CRUD
    List<Cliente> listarClientes();
    Cliente buscarClientePorId(Integer codigo);
    void guardarCliente(Cliente cliente);
    void eliminarCliebte(Cliente cliente);
}
