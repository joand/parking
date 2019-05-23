package fr.joand.parking.core;

import fr.joand.parking.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class TimeTest {

    @Autowired
    private Time time;

    @Test
    public void getRemainingMinutes() {
        LocalTime debut = LocalTime.of(12, 12);
        LocalTime fin = LocalTime.of(12, 22);
        Duration duration = Duration.between(debut, fin);
        long expected = 10;
        long actual = time.getRemainingMinutes(duration);
        assertEquals(expected, actual);
    }

    @Test
    public void isStartedHour() {
        LocalTime debut = LocalTime.of(12, 12);
        LocalTime fin = LocalTime.of(12, 22);
        Duration duration = Duration.between(debut, fin);

        assertTrue(time.isStartedHour(duration));
    }

    @Test
    public void notStartedHour() {
        LocalTime debut = LocalTime.of(12, 12);
        LocalTime fin = LocalTime.of(13, 12);
        Duration duration = Duration.between(debut, fin);

        assertFalse(time.isStartedHour(duration));
    }


    @Test
    public void isStarted30minutesA() {
        LocalTime debut = LocalTime.of(12, 12);
        LocalTime fin = LocalTime.of(12, 22);
        Duration duration = Duration.between(debut, fin);

        assertTrue(time.isStarted30minutes(duration));
    }


    @Test
    public void isStarted30minutesB() {
        LocalTime debut = LocalTime.of(0, 0);
        LocalTime fin = LocalTime.of(0, 31);
        Duration duration = Duration.between(debut, fin);

        assertTrue(time.isStarted30minutes(duration));
    }


    @Test
    public void notStarted30minutesA() {
        LocalTime debut = LocalTime.of(0, 0);
        LocalTime fin = LocalTime.of(1, 0);
        Duration duration = Duration.between(debut, fin);

        assertFalse(time.isStarted30minutes(duration));
    }


    @Test
    public void notStarted30minutesB() {
        LocalTime debut = LocalTime.of(0, 0);
        LocalTime fin = LocalTime.of(1, 30);
        Duration duration = Duration.between(debut, fin);

        assertFalse(time.isStarted30minutes(duration));
    }

    @Test
    public void simpleBetween() {
        long expected = 5;

        LocalTime debut = LocalTime.of(0, 0);
        LocalTime fin = LocalTime.of(0, 5);
        long actual = time.between(debut, fin).toMinutes();

        assertEquals(expected, actual);
    }

    @Test
    public void smartBetween() {
        long expected = 5 * 60 + 7;

        LocalTime debut = LocalTime.of(19, 30);
        LocalTime fin = LocalTime.of(0, 37);
        long actual = time.between(debut, fin).toMinutes();

        assertEquals(expected, actual);
    }

}
