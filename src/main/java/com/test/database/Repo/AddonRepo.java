package com.test.database.Repo;

import com.test.database.Model.AddonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddonRepo extends JpaRepository<AddonEntity, Integer> {

}
