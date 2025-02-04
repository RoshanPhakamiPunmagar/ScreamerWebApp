package com.example.databasedao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MoviesRepository extends JpaRepository<Movies, Long> {

    List<Movies> findByBlocked(boolean blocked);
}
