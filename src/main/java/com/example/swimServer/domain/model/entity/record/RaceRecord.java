package com.example.swimServer.domain.model.entity.record;

import com.example.swimServer.domain.model.entity.swimmer.SwimmerHistory;
import com.example.swimServer.domain.model.valueObject.raceType.RaceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Embeddable
@Table(name = "race")
@Builder
public class RaceRecord implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    private RaceType raceType;

    @Getter
    private float time_s;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private SwimmerHistory swimmer;

    public String toString(){
        return "SwimmerId: " + swimmer.getId() + ", SwimStyle: " + raceType.getSwimStyle() + ", Distance: " + raceType.getDistance() + ", Time: " + time_s;
    }
}
