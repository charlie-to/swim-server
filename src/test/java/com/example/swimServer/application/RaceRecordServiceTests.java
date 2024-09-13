package com.example.swimServer.application;

import com.example.swimServer.domain.model.entity.record.RaceRecord;
import com.example.swimServer.domain.model.entity.swimmer.SwimmerHistory;
import com.example.swimServer.domain.service.RaceRecordService;
import com.example.swimServer.infrastructure.persistance.maria.raceRecord.RaceRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class RaceRecordServiceTests {

    @Mock
    private RaceRecordRepository raceRecordRepository;
    @InjectMocks
    private RaceRecordService raceRecordService;

    @Test
    public void testGetRecordById() {
        // given
        RaceRecord raceRecord = new RaceRecord();
        raceRecord.setId(1L);
        raceRecord.setSwimmer(new SwimmerHistory());
        given(raceRecordRepository.findById(1L)).willReturn(Optional.of(raceRecord));

        // when
        RaceRecord record = raceRecordService.getRecord(1L);

        // then
        assertEquals(1, record.getId());
    }
}
