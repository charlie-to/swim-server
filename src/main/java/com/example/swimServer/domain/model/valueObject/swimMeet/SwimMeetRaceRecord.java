package com.example.swimServer.domain.model.valueObject.swimMeet;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = SwimMeetRaceRecordDeserialization.class)
public class SwimMeetRaceRecord {
    public List<RaceRecordWebDto> raceRecords;

    public SwimMeetRaceRecord() {
        this.raceRecords = new ArrayList<>();
    }

}
