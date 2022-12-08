package com.pointsystem;

import com.event.Event;
import com.event.factory.EventFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointSystemTest {

    @Test
    void calculatePointsForHundredMetresEvent(){
        Event hundredMetres = EventFactory.createEvent("100 m");
        int expectedResult = 536;
        int actualResult = hundredMetres.getPointSystem().calculatePoints(12.61);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculatePointsForLongJumpEvent() {
        Event longJump = EventFactory.createEvent("Long jump");
        int expectedResult = 382;
        int actualResult = longJump.getPointSystem().calculatePoints(5);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculatePointsForShotPutEvent() {
        Event shotPut = EventFactory.createEvent("Shot put");
        int expectedResult = 439;
        int actualResult = shotPut.getPointSystem().calculatePoints(9.22);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculatePointsForHighJumpEvent() {
        Event highJump = EventFactory.createEvent("high jump");
        int expectedResult = 389;
        int actualResult = highJump.getPointSystem().calculatePoints(1.5);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculatePointsForFourHundredMetresEvent() {
        Event fourHundredMetres = EventFactory.createEvent("400 m");
        int expectedResult = 400;
        int actualResult = fourHundredMetres.getPointSystem().calculatePoints(60.39);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculatePointsForOneHundredTenMetresHurdlesEvent() {
        Event oneHundredTenMetresHurdles = EventFactory.createEvent("110 m hurdles");
        int expectedResult = 685;
        int actualResult = oneHundredTenMetresHurdles.getPointSystem().calculatePoints(16.43);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculatePointsForDiscusThrowEvent() {
        Event discusThrow = EventFactory.createEvent("Discus Throw");
        int expectedResult = 302;
        int actualResult = discusThrow.getPointSystem().calculatePoints(21.6);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculatePointsForPoleVaultEvent() {
        Event discusThrow = EventFactory.createEvent("pole vault");
        int expectedResult = 264;
        int actualResult = discusThrow.getPointSystem().calculatePoints(2.6);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculatePointsForJavelinThrowEvent() {
        Event discusThrow = EventFactory.createEvent("Javelin throw");
        int expectedResult = 382;
        int actualResult = discusThrow.getPointSystem().calculatePoints(35.81);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculatePointsForOneThousandFiveHundredMetresEvent() {
        Event discusThrow = EventFactory.createEvent("1500 m");
        int expectedResult = 421;
        int actualResult = discusThrow.getPointSystem().calculatePoints(325.72);
        assertEquals(expectedResult, actualResult);
    }
}