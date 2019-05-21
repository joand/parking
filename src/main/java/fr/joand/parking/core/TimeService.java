package fr.joand.parking.core;

import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
class TimeService implements Time {

    @Override
    public boolean isStartedHour(Duration duration) {
        long remainingMinutes = getRemainingMinutes(duration);
        return 0 < remainingMinutes && remainingMinutes < 60;
    }

    @Override
    public boolean isStarted30minutes(Duration duration) {
        long remainingMinutes = getRemainingMinutes(duration);
        return remainingMinutes != 0 && remainingMinutes != 30; // todo ? minutes != 0
    }

    @Override
    public long getRemainingMinutes(Duration duration) {
        return duration
                .minusHours(duration.toHours())
                .toMinutes();
    }
}
