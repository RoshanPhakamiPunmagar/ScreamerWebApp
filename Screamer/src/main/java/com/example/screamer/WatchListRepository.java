package com.example.screamer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
/**
 *
 * @author Roshan Phakami Punmagar
 * File Name: WatchList.java
 * Date :16/9/2024
 * Purpose :
 * Entity Class For WatchList
 * ******************************************************
 */
@RepositoryRestResource
public interface WatchListRepository extends JpaRepository<WatchList, Integer> {
}
