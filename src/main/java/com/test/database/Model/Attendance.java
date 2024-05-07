package com.test.database.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Set<LocalDate> date;

    @ElementCollection
    @CollectionTable(name="check_times", joinColumns=@JoinColumn(name="time_id"))
    @MapKeyColumn(name="name")
    private Map<LocalDate, List<LocalTime>> times = new HashMap<>();

    public Attendance(){}

}

