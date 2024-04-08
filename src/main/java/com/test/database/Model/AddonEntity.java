package com.test.database.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Data
@Entity
public class AddonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addonId;

    private String addonName;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<FeatureEntity> features;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PlanEntity> inPlans;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<RoleEntity> mappedRoles;




}
