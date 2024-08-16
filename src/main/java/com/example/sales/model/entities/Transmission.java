package com.example.sales.model.entities;

import com.example.sales.model.enums.TransmissionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_TRANSMISSION")
public class Transmission implements Serializable {

    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Integer gears;

    @Enumerated(EnumType.STRING)
    private TransmissionEnum type;

    public Transmission (Long id){this.id = id;}
}

