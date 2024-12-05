package com.example.swimServer.domain.model.valueObject.swimMeet;

import com.example.swimServer.config.JacksonConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;

public class SwimMeetRaceTeamRecordDeserialization extends JsonDeserializer<SwimMeetRaceTeamRecord> {
    @Override
    public SwimMeetRaceTeamRecord deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException, IOException {
        SwimMeetRaceTeamRecord swimMeetRaceTeamRecord = new SwimMeetRaceTeamRecord();
        Root root = jsonParser.readValueAs(Root.class);

        for (ResultListItem resultListItem: root.items) {
            for (RecordItem recordItem: resultListItem.records) {
                if(recordItem.swimmer.entryGroup == null) {
                    // TODO: リレーの場合の処理を追加
                    System.out.println("リレーをスキップしました");
                    continue;
                }
                RaceRecordWebDto raceRecordWebDto = new RaceRecordWebDto(recordItem.swimmer.swimmerName,
                        recordItem.swimmer.swimmerCode,
                        recordItem.swimmer.entryGroup.groupCode,
                        recordItem.swimmer.entryGroup.groupName,
                        recordItem.resultTime);
                for (LapDetail lapDetail: recordItem.lapDetails) {
                    raceRecordWebDto.addLapDetail(lapDetail.time.time, lapDetail.lapDistance);
                }
                swimMeetRaceTeamRecord.addRaceRecord(raceRecordWebDto);
                System.out.println(raceRecordWebDto.ToString());
            }
        }
        return swimMeetRaceTeamRecord;
    }

    public static class Root {
        @JsonProperty("results")
        public List<ResultListItem> items;
    }

    public static class ResultListItem {
        @JsonProperty("gender")
        public Gender gender;
        @JsonProperty("swimming_style")
        public SwimmingStyle swimmingStyle;
        @JsonProperty("distances")
        public Distance distance;
        @JsonProperty("classes")
        public ClassItem classItem;
        @JsonProperty("race_division")
        public RaceDivision raceDivision;
        @JsonProperty("records")
        public List<RecordItem> records;
    }

    public static class Gender {
        @JsonProperty("name")
        public String name;
    }

    public static class SwimmingStyle {
        @JsonProperty("name")
        public String name;
    }

    public static class Distance {
        @JsonProperty("name")
        public String name;
    }

    public static class ClassItem {
        @JsonProperty("name")
        public String name;
    }

    public static class RaceDivision {
        @JsonProperty("name")
        public String name;
    }

    public static class RecordItem {
        @JsonProperty("swimmers")
        public Swimmer swimmer;
        @JsonProperty("result_time")
        public String resultTime;
        @JsonProperty("result_date")
        public String resultDate;
        @JsonProperty("lap_detail")
        public List<LapDetail> lapDetails;
    }

    public static class Swimmer {
        @JsonProperty("swimmer_name")
        public String swimmerName;
        @JsonProperty("swimmer_code")
        public String swimmerCode;
        @JsonProperty("entry_group")
        public Group entryGroup;
        // リレーの場合
        @JsonProperty("team_name")
        public String teamName;
        @JsonProperty("team_members")
        public List<Swimmer> teamMembers;
    }

    public static class Group {
        @JsonProperty("code")
        public String groupCode;
        @JsonProperty("name")
        public String groupName;
    }

    public static class LapDetail {
        @JsonProperty("lap_distance")
        public int lapDistance;
        @JsonProperty("passing_time")
        public LapTime time;
    }

    public static class LapTime {
        @JsonProperty("passing_time")
        public String time;
    }
}
