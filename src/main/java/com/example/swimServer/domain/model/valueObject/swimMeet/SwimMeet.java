package com.example.swimServer.domain.model.valueObject.swimMeet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@JsonDeserialize(using = SwimMeetDeserializer.class)
public class SwimMeet {

    private final Long gameCode;
    private final String gameName;
    private final String startDate;
    private final String endDate;
    private final String waterWay;
    private final String memberGroup;
    private final String poolName;
    private final Boolean isOfficialGames;


    @JsonCreator
    public SwimMeet(
            Long gameCode,
            String gameName,
            String startDate,
            String endDate,
            String waterWay,
            String memberGroup,
            String poolName,
            Boolean isOfficialGames
    ) {

        this.gameCode = gameCode;
        this.gameName = gameName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.waterWay = waterWay;
        this.memberGroup = memberGroup;
        this.poolName = poolName;
        this.isOfficialGames = isOfficialGames;
    }
}
