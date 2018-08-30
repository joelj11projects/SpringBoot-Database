package com.qa.SpringBoot.JJ.Database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.SpringBoot.JJ.Database.model.SpringBootDataModel;

@Repository
public interface SpringBootRepo extends JpaRepository<SpringBootDataModel, Long>{

}
