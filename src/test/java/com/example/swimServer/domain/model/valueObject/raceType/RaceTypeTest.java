package com.example.swimServer.domain.model.valueObject.raceType;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
public class RaceTypeTest {

        private final Integer distance = 100;
        private final SwimStyle swimStyle = SwimStyle.FREESTYLE;


        @Test
        @Tag("small")
        void CanSetNormalValue(){
                RaceType raceType = new RaceType(distance, swimStyle);
                assert raceType.getDistance().equals(distance);
                assert raceType.getSwimStyle() == swimStyle;
        }

        @Test
        @Tag("small")
        void CanSetDistance(){
                RaceType raceType = new RaceType(distance, swimStyle);
                Integer newDistance = 200;

                RaceType newRaceType = raceType.setDistance(newDistance);

                assert newRaceType.getDistance().equals(newDistance);
                assert newRaceType.getSwimStyle() == swimStyle;
        }

        @Test
        @Tag("small")
        void CanSetSwimStyle() {
                RaceType raceType = new RaceType(distance, swimStyle);
                SwimStyle newSwimStyle = SwimStyle.BACKSTROKE;

                RaceType newRaceType = raceType.setSwimStyle(newSwimStyle);

                assert newRaceType.getDistance().equals(distance);
                assert newRaceType.getSwimStyle() == newSwimStyle;
        }

        @ParameterizedTest
        @Tag("small")
        @CsvSource({
                "3000, FREESTYLE",
                "0, FREESTYLE",
                "125, FREESTYLE",
                "800, BACKSTROKE",
                "400, BACKSTROKE",
                "300, BACKSTROKE",
                "800, MEDLEY_RELAY",
        }) // invalid distance
        void CanNotSetInvalidDistance(int newDistance, SwimStyle newSwimStyle){
                assertThrows(IllegalArgumentException.class, () -> {
                        RaceType raceType = new RaceType(distance, swimStyle);
                        RaceType newRaceType = raceType.setDistance(newDistance);
                        newRaceType.setDistance(newDistance);
                });
        }
}

