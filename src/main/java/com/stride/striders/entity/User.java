package com.stride.striders.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String gender;
    @NotNull
    @NotEmpty
    @Column(unique = true, name = "email")
    private String email;
    @NotNull
    @NotEmpty
    private String nationality;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;
    

}
