package com.example.swimServer.domain.model.valueObject.swimMeet;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = SwimMeetRaceTeamRecordDeserialization.class)
public class SwimMeetRaceTeamRecord {
    public List<RaceRecordWebDto> raceRecords;

    public SwimMeetRaceTeamRecord() {
        this.raceRecords = new ArrayList<>();
    }

    public void addRaceRecord(RaceRecordWebDto raceRecord) {
        raceRecords.add(raceRecord);
    }
}
