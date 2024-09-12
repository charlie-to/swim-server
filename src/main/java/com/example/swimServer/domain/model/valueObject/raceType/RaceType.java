package com.example.swimServer.domain.model.valueObject.raceType;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Embeddable
@Builder
public class RaceType implements java.io.Serializable {
    protected  Integer distance_m;
    protected  SwimStyle swimStyle;

    public RaceType() {
    }

    public RaceType(Integer distance_m, SwimStyle swimStyle) {
        // 25m - 1500m　の範囲で、25m,50m,100m,200m,400m,800m,1500mのみ
        if (distance_m < 25 || distance_m > 1500) {
            throw new IllegalArgumentException("Invalid distance");
        }
        if (distance_m != 25 && distance_m != 50 && distance_m != 100 && distance_m != 200 && distance_m != 400 && distance_m != 800 && distance_m != 1500) {
            throw new IllegalArgumentException("Invalid distance");
        }
        // 1500mはフリースタイルのみ
        if (distance_m == 1500 && swimStyle != SwimStyle.FREESTYLE) {
            throw new IllegalArgumentException("Invalid distance");
        }
        // 800mはフリースタイルとフリーリレーのみ
        if ((distance_m == 800 && swimStyle != SwimStyle.FREESTYLE) || (distance_m == 800 && swimStyle != SwimStyle.FREESTYLE_RELAY)) {
            throw new IllegalArgumentException("Invalid distance");
        }
        // 400mはフリースタイル、個人メドレー、リレー種目のみ
        if ((distance_m == 400 && swimStyle != SwimStyle.FREESTYLE) || (distance_m == 400 && swimStyle != SwimStyle.MEDLEY) || (distance_m == 400 && swimStyle != SwimStyle.FREESTYLE_RELAY) || (distance_m == 400 && swimStyle != SwimStyle.MEDLEY_RELAY)) {
            throw new IllegalArgumentException("Invalid distance");
        }
        // 200mは全ての種目なのでチェック不要
        // 100mは全ての種目なのでチェック不要
        // 50mはリレー種目はない
        if (distance_m == 50 && swimStyle == SwimStyle.FREESTYLE_RELAY) {
            throw new IllegalArgumentException("Invalid distance");
        }
        if (distance_m == 50 && swimStyle == SwimStyle.MEDLEY_RELAY) {
            throw new IllegalArgumentException("Invalid distance");
        }
        // 25mはリレー種目はない
        if (distance_m == 25 && swimStyle == SwimStyle.FREESTYLE_RELAY) {
            throw new IllegalArgumentException("Invalid distance");
        }
        if (distance_m == 25 && swimStyle == SwimStyle.MEDLEY_RELAY) {
            throw new IllegalArgumentException("Invalid distance");
        }

        this.distance_m = distance_m;
        this.swimStyle = swimStyle;
    }

    public RaceType setDistance(Integer distance_m) {
        return new RaceType(distance_m, this.swimStyle);
    }

    public RaceType setSwimStyle(SwimStyle swimStyle) {
        return new RaceType(this.distance_m, swimStyle);
    }

    public Integer getDistance() {
        return distance_m;
    }

    public SwimStyle getSwimStyle() {
        return swimStyle;
    }
}