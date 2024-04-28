package com.example.sales.repository;

import com.example.sales.model.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    @Query(" select m from Model m where m.brand.id = :idBrand")
    public List<Model> listModelByIdBrand(@Param("idBrand") Long idBrand);
}
