package com.example.swimServer.domain.model.entity.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

}