package com.example.sales.model.entities;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "Username é obrigatório")
    @Size(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    @Column(nullable = false, length = 255)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public User( String username, String password){
        this.username = username;
        this.password = password;
    }
}
