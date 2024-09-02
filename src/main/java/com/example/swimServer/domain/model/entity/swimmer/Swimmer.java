package com.example.swimServer.domain.model.entity.swimmer;

import jakarta.persistence.*;

@Entity
@Table(name = "swimmer")
public class Swimmer  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private  String familyName;
    private  String givenName;

    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public String toJson() {
        return "{\"id\":" + id + ",\"familyName\":\"" + familyName + "\",\"givenName\":\"" + givenName + "\",\"age\":" + age + "}";
    }
}
