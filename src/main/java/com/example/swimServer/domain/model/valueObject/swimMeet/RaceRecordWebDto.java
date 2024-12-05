package com.example.swimServer.domain.model.valueObject.swimMeet;

import java.util.ArrayList;
import java.util.List;

public class RaceRecordWebDto {
    // swimmer Information
    private final String swimmerName;
    private final String swimmerCode;
    private final String swimmerGroupCode;
    private final String swimmerGroupName;

    // Time Information
    private final String resultTime;

    // Lap Information
    private final List<LapDetail> lapDetails;

    public RaceRecordWebDto(String swimmerName,
                            String swimmerCode,
                            String swimmerGroupCode,
                            String swimmerGroupName,
                            String resultTime) {
        this.swimmerName = swimmerName;
        this.swimmerCode = swimmerCode;
        this.swimmerGroupCode = swimmerGroupCode;
        this.swimmerGroupName = swimmerGroupName;
        this.resultTime = resultTime;
        this.lapDetails = new ArrayList<>();
    }

    public void addLapDetail(String lapTime, int lapDistance) {
        LapDetail lapDetail = new LapDetail();
        lapDetail.lapTime = lapTime;
        lapDetail.lapDistance = lapDistance;
        lapDetails.add(lapDetail);
    }

    public String ToString() {
        return "Swimmer Name: " + swimmerName + ", Swimmer Code: " + swimmerCode + ", Swimmer Group Code: " + swimmerGroupCode + ", Swimmer Group Name: " + swimmerGroupName + ", Result Time: " + resultTime;
    }

    private static class LapDetail {
        private String lapTime;
        private int lapDistance;
    }
}
