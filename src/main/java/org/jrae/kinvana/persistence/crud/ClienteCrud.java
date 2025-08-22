package org.jrae.kinvana.persistence.crud;

import org.jrae.kinvana.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteCrud extends JpaRepository<Cliente, Integer> {
    //sustituye los DAO
    //Esta interface tiene todos los metodos genericos del CRUD

}

