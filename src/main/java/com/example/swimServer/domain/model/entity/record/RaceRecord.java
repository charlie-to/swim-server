package com.example.swimServer.domain.model.entity.record;

import com.example.swimServer.domain.model.valueObject.raceType.RaceType;
import com.example.swimServer.domain.model.valueObject.raceType.SwimStyle;
import com.example.swimServer.interfaces.dto.RecordDto;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "race")
public class RaceRecord implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long swimmerId;

    @Embedded
    private RaceType raceType;

    private float time_s;

    public RaceRecord(){}

    public RaceRecord(RecordDto recordDto){
        this.swimmerId = recordDto.swimmerId;
        this.raceType = new RaceType(recordDto.distance, SwimStyle.valueOf(recordDto.swimStyle.toUpperCase()));
        this.time_s = recordDto.time_s;
    }

    public Long getSwimmerId() {
        return swimmerId;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public float getTime_s() {
        return time_s;
    }
}
