package com.example.swimServer.domain.model.entity.user;

import com.example.swimServer.domain.model.entity.swimmer.SwimmerHistory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<SwimmerHistory> swimmers = new ArrayList<>();
}