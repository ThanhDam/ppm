package com.csc.team2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.File;

@Repository
public interface IFileRepository extends JpaRepository<File,Integer> {

}
