package com.test.database.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class FeatureEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int featureId;

    private String featureName;

    @ManyToMany
    @JsonIgnore
    private Set<AddonEntity> addon;

}
