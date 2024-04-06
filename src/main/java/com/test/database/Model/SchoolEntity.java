package com.test.database.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Data
@Entity
public class SchoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolId;

    private String schoolName;

    @ManyToOne
    private PlanEntity plan;

    @OneToMany
    private Set<UserEntity> students;


}
