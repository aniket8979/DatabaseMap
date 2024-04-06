package com.test.database.Repo;

import com.test.database.Model.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepo extends JpaRepository<SchoolEntity, Integer> {
}
