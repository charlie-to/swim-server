package com.example.swimServer.infrastructure.persistance.maria.swimmer;

import com.example.swimServer.domain.model.entity.swimmer.SwimmerHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwimmerRepository extends CrudRepository<SwimmerHistory, Long> {
}