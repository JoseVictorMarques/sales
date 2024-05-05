package com.example.sales.repository;

import com.example.sales.model.entities.Model;
import com.example.sales.model.entities.ModelVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModelVersionRepository extends JpaRepository<ModelVersion, Long> {

    @Query(" select mv from ModelVersion mv where mv.model.id = :idModel")
    public List<ModelVersion> listVersionByIdModel(@Param("idModel") Long idModel);
}
