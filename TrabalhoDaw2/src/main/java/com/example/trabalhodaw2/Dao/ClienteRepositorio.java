package com.example.trabalhodaw2.Dao;

import com.example.trabalhodaw2.Model.Cliente;
import com.example.trabalhodaw2.Model.Lance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Cliente findById(long id);

    @Query(value = "SELECT * FROM cliente l WHERE l.email = ?1", nativeQuery = true)
    Cliente findByEmail(String email);

}
