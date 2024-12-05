package com.example.swimServer.domain.model.valueObject.swimMeet;

import com.example.swimServer.domain.model.valueObject.raceType.RaceType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = SwimMeetRacesDeserializer.class)
public class SwimMeetRace {
    public List<RaceType> raceTypes;
    public SwimMeetRace() {
        this.raceTypes = new ArrayList<>();
    }

}
