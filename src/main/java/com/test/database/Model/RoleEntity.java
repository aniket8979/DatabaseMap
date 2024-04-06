package com.test.database.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    private String roleName;

    private boolean read;

    private boolean write;

    private boolean delete = false;

    private String roleType;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AddonEntity> addons;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UserEntity> mappedUser;

}