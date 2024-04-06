package com.test.database.Repo;

import com.test.database.Model.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepo extends JpaRepository<PlanEntity, Integer> {
}
