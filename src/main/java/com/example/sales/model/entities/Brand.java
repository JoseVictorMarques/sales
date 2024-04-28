package com.example.sales.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="TB_BRAND")
public class Brand implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    public Brand( Long id){
        this.id = id;
    }
}
