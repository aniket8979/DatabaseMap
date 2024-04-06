package com.test.database.Model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String Name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId")
    private RoleEntity role;



}
