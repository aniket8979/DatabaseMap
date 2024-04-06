package com.test.database.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class PlanEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;

    private String planName;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AddonEntity> usedAddons;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SchoolEntity> schools;

}
