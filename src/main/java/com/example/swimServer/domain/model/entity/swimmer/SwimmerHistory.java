package com.example.swimServer.domain.model.entity.swimmer;

import com.example.swimServer.domain.model.entity.record.RaceRecord;
import com.example.swimServer.domain.model.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Embeddable
@Table(name = "swimmer")
public class SwimmerHistory {
    @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    private  String familyName;
    @Getter
    @Setter
    private  String givenName;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private Date joinDate;

    @Getter
    @Setter
    private Date leaveDate;

    @OneToMany(mappedBy = "swimmer")
    private List<RaceRecord> raceRecords = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public String toJson() {
        return "{\"id\":" + id + ",\"familyName\":\"" + familyName + "\",\"givenName\":\"" + givenName + "\",\"age\":" + age + "}";
    }
}
