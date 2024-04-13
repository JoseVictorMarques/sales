package com.example.sales.model.entities;

import com.example.sales.model.enums.TransmissionEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="TB_CAR")
public class Car implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @Column(nullable = false)
    private Integer manufacturingYear;
}
