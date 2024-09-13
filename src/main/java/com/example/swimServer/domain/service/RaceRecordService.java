package com.example.swimServer.domain.service;

import com.example.swimServer.domain.model.entity.record.RaceRecord;
import com.example.swimServer.domain.model.entity.swimmer.SwimmerHistory;
import com.example.swimServer.domain.model.valueObject.raceType.RaceType;
import com.example.swimServer.domain.model.valueObject.raceType.SwimStyle;
import com.example.swimServer.infrastructure.persistance.maria.raceRecord.RaceRecordRepository;
import com.example.swimServer.infrastructure.persistance.maria.swimmer.SwimmerRepository;
import com.example.swimServer.interfaces.dto.RecordDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RaceRecordService {
    @Autowired
    private RaceRecordRepository raceRecordRepository;

    @Autowired
    private SwimmerRepository swimmerRepository;

    public void addRecord(RecordDto recordDto){
        SwimmerHistory swimmer = swimmerRepository.findById(recordDto.swimmerId).orElse(null);
        if(swimmer == null){
            throw new IllegalArgumentException("Swimmer not found");
        }
        RaceRecord record = RaceRecord.builder()
                .swimmer(swimmer)
                .raceType(RaceType.builder().distance_m(recordDto.distance).swimStyle(SwimStyle.valueOf(recordDto.swimStyle.toUpperCase())).build())
                .time_s(recordDto.time_s)
                .build();
        raceRecordRepository.save(record);
    }

    public RaceRecord getRecord(Long id){
        return raceRecordRepository.findById(id).orElse(null);
    }

    public List<RaceRecord> getAllRecords(){
        return (List<RaceRecord>) raceRecordRepository.findAll();
    }
}
