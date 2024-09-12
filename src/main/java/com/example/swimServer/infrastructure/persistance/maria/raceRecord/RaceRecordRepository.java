package com.example.swimServer.infrastructure.persistance.maria.raceRecord;

import com.example.swimServer.domain.model.entity.record.RaceRecord;
import org.springframework.data.repository.CrudRepository;

public interface RaceRecordRepository extends CrudRepository<RaceRecord, Long> {
}
