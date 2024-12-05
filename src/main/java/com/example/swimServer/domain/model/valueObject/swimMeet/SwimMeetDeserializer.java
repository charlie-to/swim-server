package com.example.swimServer.domain.model.valueObject.swimMeet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SwimMeetDeserializer extends JsonDeserializer<SwimMeet> {
    @Override
    public SwimMeet deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Root root = jsonParser.readValueAs(Root.class);
        return new SwimMeet(
                root.gameCode,
                root.gameName,
                root.startDate,
                root.endDate,
                root.waterWay.waterWay,
                root.memberGroup.memberGroup,
                root.poolName,
                root.isOfficialGames
        );
    }

    private static class Root{
        @JsonProperty("game_code")
        public Long gameCode;
        @JsonProperty("game_name")
        public String gameName;
        @JsonProperty("start_date")
        public String startDate;
        @JsonProperty("end_date")
        public String endDate;
        @JsonProperty("waterway")
        public WaterWay waterWay;
        @JsonProperty("member_group")
        public MemberGroup memberGroup;
        @JsonProperty("pool")
        public String poolName;
        @JsonProperty("is_official_games")
        public Boolean isOfficialGames;
    }

    private static class MemberGroup{
        @JsonProperty("name")
        public String memberGroup;
    }

    private static class WaterWay {
        @JsonProperty("name")
        public String waterWay;
    }
}
