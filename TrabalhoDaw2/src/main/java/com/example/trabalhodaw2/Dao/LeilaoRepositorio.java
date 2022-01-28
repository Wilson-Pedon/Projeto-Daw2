package com.example.trabalhodaw2.Dao;

import com.example.trabalhodaw2.Model.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface LeilaoRepositorio extends JpaRepository<Leilao, Long> {

    Leilao findById(long id);

    @Query(value = "SELECT * FROM leilao l WHERE l.status = ?1", nativeQuery = true)
    public List<Leilao> findLeilaoByStatus(String status);

    @Query(value = "SELECT * FROM leilao l WHERE l.status = ?1", nativeQuery = true)
    public List<Leilao> findLeilaoByDataAtrasada(String status);

    @Query(value = "SELECT * FROM leilao l WHERE l.status = 'ABERTO'", nativeQuery = true)
    public List<Leilao> findLeilaoByStatusAberto();


}