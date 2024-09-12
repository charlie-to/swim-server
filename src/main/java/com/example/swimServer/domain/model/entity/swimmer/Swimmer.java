package com.example.swimServer.domain.model.entity.swimmer;

import com.example.swimServer.domain.model.entity.record.RaceRecord;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "swimmer")
public class Swimmer  {
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

    @OneToMany(mappedBy = "swimmer")
    private List<RaceRecord> raceRecords = new ArrayList<>();

    public String toJson() {
        return "{\"id\":" + id + ",\"familyName\":\"" + familyName + "\",\"givenName\":\"" + givenName + "\",\"age\":" + age + "}";
    }
}
