package fr.joand.parking.core;

import java.time.Duration;

public interface Time {
    boolean isStartedHour(Duration duration);

    boolean isStarted30minutes(Duration duration);

    long getRemainingMinutes(Duration duration);
}
