package com.example.sales.model.entities;

import com.example.sales.model.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_PERSON")
public class Person implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 400)
    private String fullName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 20)
    private String cpf;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 5)
    private String ddd;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

}
