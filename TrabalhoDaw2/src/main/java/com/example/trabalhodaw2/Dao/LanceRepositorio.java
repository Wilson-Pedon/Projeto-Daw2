package com.example.trabalhodaw2.Dao;

import com.example.trabalhodaw2.Model.Lance;
import com.example.trabalhodaw2.Model.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LanceRepositorio extends JpaRepository<Lance, Long> {

    Lance findById(long id);

    @Query(value = "SELECT * FROM lance l WHERE l.id_leilao = ?1", nativeQuery = true)
    public List<Lance> findLeilaoByIdLeilao(long id);

}
