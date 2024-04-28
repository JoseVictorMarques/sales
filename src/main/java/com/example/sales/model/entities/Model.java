package com.example.sales.model.entities;

import com.example.sales.model.dtos.ModelDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="TB_MODEL")
public class Model implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public ModelDTO toDTO(){
        ModelDTO modelDTO = new ModelDTO();
        modelDTO.setId(this.id);
        modelDTO.setName(this.name);
        modelDTO.setBrandId(this.brand.getId());
        return modelDTO;
    }
}
