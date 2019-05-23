package fr.joand.parking.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
class TimeService implements Time {

    private final Logger logger = LoggerFactory.getLogger(TimeService.class);

    @Override
    public boolean isStartedHour(Duration duration) {
        long remainingMinutes = getRemainingMinutes(duration);
        return 0 < remainingMinutes && remainingMinutes < 60;
    }

    @Override
    public boolean isStarted30minutes(Duration duration) {
        long remainingMinutes = getRemainingMinutes(duration);
        return remainingMinutes != 0 && remainingMinutes != 30;
    }

    @Override
    public long getRemainingMinutes(Duration duration) {
        return duration
                .minusHours(duration.toHours())
                .toMinutes();
    }

    @Override
    public Duration between(LocalTime debut, LocalTime fin) {
        if (debut.isBefore(fin)) {
            return Duration.between(debut, fin);
        } else {
            logger.info("smart Duration computing : adding one day to end input ;)");
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);

            LocalDateTime start = LocalDateTime.of(today, debut);
            LocalDateTime end = LocalDateTime.of(tomorrow, fin);
            return Duration.between(start, end);
        }
    }
}
