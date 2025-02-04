package com.example.databasedao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface WatchListRepository extends JpaRepository<WatchList, Long> {

    WatchList findBycustomer(Customer customer);
}
