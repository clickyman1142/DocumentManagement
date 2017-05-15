package com.fsoft.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.exercise.entities.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {
	
}
