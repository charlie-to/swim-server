package com.example.swimServer.infrastructure.persistance.maria.swimmer;

import com.example.swimServer.domain.model.swimmer.Swimmer;
import org.springframework.data.repository.CrudRepository;

public interface SwimmerRepository extends CrudRepository<Swimmer, Integer> {
}