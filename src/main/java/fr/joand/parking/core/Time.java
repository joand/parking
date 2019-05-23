package fr.joand.parking.core;

import java.time.Duration;
import java.time.LocalTime;

public interface Time {
    boolean isStartedHour(Duration duration);

    boolean isStarted30minutes(Duration duration);

    long getRemainingMinutes(Duration duration);

    Duration between(LocalTime debut, LocalTime fin);

    String format(Duration duration);
}
