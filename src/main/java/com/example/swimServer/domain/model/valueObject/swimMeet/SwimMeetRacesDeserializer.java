package com.example.swimServer.domain.model.valueObject.swimMeet;

import com.example.swimServer.domain.model.valueObject.raceType.RaceType;
import com.example.swimServer.domain.model.valueObject.raceType.SwimStyle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;

public class SwimMeetRacesDeserializer extends JsonDeserializer<SwimMeetRace> {
    @Override
    public SwimMeetRace deserialize(JsonParser jsonParser, DeserializationContext deserializationContext ) throws IOException, JsonProcessingException {
        SwimMeetRace swimMeetRace = new SwimMeetRace();
        Root root = jsonParser.readValueAs(Root.class);
        for(Data data : root.data){
            for(RaceGenders raceGenders : data.raceGenders){
                for(HeldStyle style: raceGenders.heldStyles){
                    SwimStyle swimStyle = null;
                    switch (style.swimStyle.swimStyleName){
                        case "自由形":
                            swimStyle = SwimStyle.FREESTYLE;
                            break;
                        case "背泳ぎ":
                            swimStyle = SwimStyle.BACKSTROKE;
                            break;
                        case "平泳ぎ":
                            swimStyle = SwimStyle.BREASTSTROKE;
                            break;
                        case "バタフライ":
                            swimStyle = SwimStyle.BUTTERFLY;
                            break;
                        case "個人メドレー":
                            swimStyle = SwimStyle.MEDLEY;
                            break;
                        case "フリーリレー":
                            swimStyle = SwimStyle.FREESTYLE_RELAY;
                            break;
                        case "メドレーリレー":
                            swimStyle = SwimStyle.MEDLEY_RELAY;
                            break;
                        case null, default:
                            throw new IllegalStateException("Unexpected value: " + style.swimStyle.swimStyleName);
                    }
                    for(HeldDistance distance: style.heldDistances) {
                        Integer distance_m = Integer.parseInt(distance.distance.distanceName.substring(0,distance.distance.distanceName.length()-1));
                        for (SwimClass swimClass : distance.classes) {
                            for(RaceDivisionItem raceDivision : swimClass.raceDivisions){
                                swimMeetRace.raceTypes.add(new RaceType(distance_m, swimStyle ));
                            }
                        }
                    }
                }
        }}
        return swimMeetRace;
    }

    private static class Root{
        @JsonProperty("data")
        public List<Data> data;
    }
    private static class Data{
        @JsonProperty("race_genders")
        public List<RaceGenders> raceGenders;
    }

    private static class RaceGenders{
        @JsonProperty("gender")
        public Gender gender;
        @JsonProperty("held_styles")
        public List<HeldStyle> heldStyles;
    }

    private static class Gender{
        @JsonProperty("name")
        public String genderName;

    }

    private static class HeldStyle {
        @JsonProperty("swimming_style")
        public SwimingStyle swimStyle;
        @JsonProperty("held_distances")
        public List<HeldDistance> heldDistances;
    }

    private static class SwimingStyle{
        @JsonProperty("name")
        public String swimStyleName;
    }

    private static class HeldDistance{
        @JsonProperty("distance")
        public Distance distance;
        @JsonProperty("classes")
        public List<SwimClass> classes;
    }
    private static class Distance{
        @JsonProperty("name")
        public String distanceName;
    }
    private static class SwimClass{
        @JsonProperty("class")
        public SwimClassItem classItem;
        @JsonProperty("race_divisions")
        public List<RaceDivisionItem> raceDivisions;
    }

    private static class SwimClassItem{
        @JsonProperty("name")
        public String className;
    }

    private static class RaceDivisionItem{
        @JsonProperty("division")
        public Division raceDivision;
    }

    private static class Division{
        @JsonProperty("name")
        public String raceDivisionName;
    }
}
