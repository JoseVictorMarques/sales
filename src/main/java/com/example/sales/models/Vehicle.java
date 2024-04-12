package com.example.sales.models;

import com.example.sales.enums.VehicleEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_VEHICLE")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleEnum vehicleType;


    @Column(nullable = false)
    private LocalDate manufacturingDate;

}
