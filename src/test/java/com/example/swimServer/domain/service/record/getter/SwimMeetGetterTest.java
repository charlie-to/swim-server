package com.example.swimServer.domain.service.record.getter;

import com.example.swimServer.domain.model.valueObject.swimMeet.SwimMeet;
import com.example.swimServer.domain.model.valueObject.swimMeet.SwimMeetRace;
import com.example.swimServer.domain.model.valueObject.swimMeet.SwimMeetRaceRecord;
import com.example.swimServer.domain.model.valueObject.swimMeet.SwimMeetRaceTeamRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
public class SwimMeetGetterTest {

    @Test
    @Tag("huge")
    @DisplayName("大会情報が取得できる")
    void CanGetMetaData() throws Exception {
        SwimMeetGetter swimMeetGetter = new SwimMeetGetter();
        String meetCode = "5324401";
        SwimMeet result = swimMeetGetter.getMeta(meetCode);
        assert Objects.nonNull(result);
        assert result.getGameCode().equals(5324401L);
    }

    @Test
    @Tag("huge")
    @DisplayName("種目が取得できる")
    void CanGetRaceTypes() throws Exception {
        SwimMeetGetter swimMeetGetter = new SwimMeetGetter();
        String meetCode = "5324401";
        SwimMeetRace result = swimMeetGetter.getRaceTypes(meetCode);
        assert Objects.nonNull(result);
    }

    @Test
    @Tag("huge")
    @DisplayName("記録が取得できる")
    void CanGetRecord() throws Exception {
        SwimMeetGetter swimMeetGetter = new SwimMeetGetter();
        String meetCode = "5324401";
        SwimMeetRaceRecord result = swimMeetGetter.getResults(meetCode, "1", "1", "2", "0", "2");
        assert Objects.nonNull(result);
        assert !result.raceRecords.isEmpty();
    }

    @Test
    @Tag("huge")
    @DisplayName("記録がチームごとに取得できる")
    void CanGetRecordByTeam() throws Exception {
        SwimMeetGetter swimMeetGetter = new SwimMeetGetter();
        String meetCode = "5324401";
        String teamCode = "53066";
        String teamName = "東北大";
        SwimMeetRaceTeamRecord result = swimMeetGetter.getResultsByTeam(meetCode, teamCode,teamName);
        assert Objects.nonNull(result);
    }
}
