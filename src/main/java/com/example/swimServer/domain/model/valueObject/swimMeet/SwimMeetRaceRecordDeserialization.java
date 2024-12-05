package com.example.swimServer.domain.model.valueObject.swimMeet;

import com.example.swimServer.domain.model.entity.record.RaceRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.List;

public class SwimMeetRaceRecordDeserialization extends JsonDeserializer<SwimMeetRaceRecord> {
    @Override
    public SwimMeetRaceRecord deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        SwimMeetRaceRecord swimMeetRaceRecord = new SwimMeetRaceRecord();
        Root root = jsonParser.readValueAs(Root.class);

        for (Data data : root.data) {
            RaceRecordWebDto raceRecordWebDto = new RaceRecordWebDto(data.swimmer.swimmerName,
                    data.swimmer.swimmerCode,
                    data.swimmer.entryGroup.groupCode,
                    data.swimmer.entryGroup.groupName,
                    data.resultTime);
            for (LapDetail lapDetail : data.lapDetail) {
                System.out.println(lapDetail.time.time);
                raceRecordWebDto.addLapDetail(lapDetail.time.time, lapDetail.lap_distance_m);
            }
            swimMeetRaceRecord.raceRecords.add(raceRecordWebDto);
        }

        return swimMeetRaceRecord;

    }

    public static class Root {
        @JsonProperty("data")
        public List<Data> data;
    }

    public static class Data {
        @JsonProperty("swimmers")
        public Swimmer swimmer;
        @JsonProperty("result_time")
        public String resultTime;
        @JsonProperty("lap_detail")
        public List<LapDetail> lapDetail;
    }

    public static class Swimmer {
        @JsonProperty("swimmer_name")
        public String swimmerName;
        @JsonProperty("swimmer_code")
        public String swimmerCode;
        @JsonProperty("entry_group")
        public Group entryGroup;

    }

    public static class Group {
        @JsonProperty("code")
        public String groupCode;
        @JsonProperty("name")
        public String groupName;
    }

    public static class LapDetail {
        @JsonProperty("lap_distance")
        public int lap_distance_m;
        @JsonProperty("passing_time")
        public Time time;
    }

    public static class Time {
        @JsonProperty("record")
        public String time;
    }
}
