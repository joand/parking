package fr.joand.parking.core;

import fr.joand.parking.pojo.CarburantType;
import fr.joand.parking.pojo.VehiculeType;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.regex.Pattern;

@Service
class ServiceFacturaion implements Facturaion {

    private Pattern integerPattern = Pattern.compile("^\\d+(\\.0+)?$");

    @Override
    public double calculerTarif(LocalTime debut, LocalTime fin, VehiculeType vehiculeType, CarburantType carburantType) {
        Duration duration = Duration.between(debut, fin);
        long nbOfHours = duration.toHours();
        if (nbOfHours < 1) {
            return 0;
        } else if (duration.toMinutes() < 4 * 60) {
            //return nbOfHours * 2 +
        }

        return 0;
    }

    @Override
    public double calculerTarifArrondi(LocalTime debut, LocalTime fin, VehiculeType vehiculeType, CarburantType carburantType) {
        return arrondirTarif(calculerTarif(debut, fin, vehiculeType, carburantType));
    }

    @Override
    public double arrondirTarif(double input) {

        double fractionalPart = getFractionalPart(input);
        if (isInteger(input)) {
            return input;
        } else if (fractionalPart < 0.5) {
            return Math.floor(input) + 0.5;
        } else if (fractionalPart == 0.5) {
            return input;
        } else {
            return Math.ceil(input);
        }
    }

    @Override
    public double getFractionalPart(double input) {
        return input % 1;
    }

    @Override
    public boolean isInteger(double input) {
        return integerPattern.matcher(Double.toString(input)).find();
    }

}
