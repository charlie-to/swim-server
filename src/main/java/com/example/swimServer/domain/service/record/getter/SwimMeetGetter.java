package com.example.swimServer.domain.service.record.getter;

import com.example.swimServer.domain.model.valueObject.swimMeet.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class SwimMeetGetter {

    private String baseUrl = "https://result.swim.or.jp/api/v1/games/";

    public SwimMeet getMeta(String meetCode) {
        String uri = baseUrl + meetCode;
        RestClient client = RestClient.create();
        String res = client.get().uri(uri).retrieve().body(String.class);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(res, SwimMeet.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SwimMeetRace getRaceTypes(String meetCode) {
        String uri = baseUrl + meetCode + "/races";
        RestClient client = RestClient.create();
        String res = client.get().uri(uri).retrieve().body(String.class);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            SwimMeetRace swimMeet = mapper.readValue(res, SwimMeetRace.class);
            return swimMeet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SwimMeetRaceRecord getResults(String meetCode, String genderCode, String swimmingStyleCode, String distanceCode, String classCode, String raceDivisionsCode) {
        String uri = baseUrl + meetCode + "/results/" + "genders/" + genderCode + "/swimming_styles/" + swimmingStyleCode + "/distances/" + distanceCode + "/classes/" + classCode + "/race_divisions/" + raceDivisionsCode + "/heats/100";
        RestClient client = RestClient.create();
        String res = client.get().uri(uri).retrieve().body(String.class);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            SwimMeetRaceRecord swimMeet = mapper.readValue(res, SwimMeetRaceRecord.class);
            return swimMeet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SwimMeetRaceTeamRecord getResultsByTeam(String meetCode, String teamCode, String teamName) {
        String urlEncordedTeamName = URLEncoder.encode(teamName, StandardCharsets.UTF_8);
        String uri = baseUrl + meetCode + "/entry_groups/" + teamCode + "/" + urlEncordedTeamName;
        RestClient client = RestClient.create();
        String res = client.get().uri(uri).retrieve().body(String.class);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            SwimMeetRaceTeamRecord swimMeet = mapper.readValue(res, SwimMeetRaceTeamRecord.class);
            return swimMeet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
