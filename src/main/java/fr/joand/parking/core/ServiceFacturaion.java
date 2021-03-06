package fr.joand.parking.core;

import fr.joand.parking.pojo.CarburantType;
import fr.joand.parking.pojo.Facture;
import fr.joand.parking.pojo.VehiculeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.regex.Pattern;

@Service
class ServiceFacturaion implements Facturaion {
    private final Logger logger = LoggerFactory.getLogger(ServiceFacturaion.class);

    private Pattern integerPattern = Pattern.compile("^\\d+(\\.0+)?$");

    @Autowired
    private Time time;

    /**
     * The high level method
     */
    @Override
    public double calculerTarifFinal(Facture facture) {
        double tarifHoraire = calculerTarifHoraire(facture);
        logger.info("tarifHoraire : " + tarifHoraire);

        double tarifBrut = appliquerTaux(facture, tarifHoraire);
        logger.info("tarifBrut : " + tarifBrut);

        double tarifArrondi = arrondirTarif(tarifBrut);
        logger.info("tarifArrondi : " + tarifArrondi);

        facture.setMontant(tarifArrondi);
        return tarifArrondi;
    }

    @Override
    public Facture build(VehiculeType vehicule, CarburantType carburant, LocalTime debut, LocalTime fin) {
        return new Facture(vehicule, carburant, time.between(debut, fin));
    }

    @Override
    public double calculerTarifHoraire(Facture facture) {
        Duration duration = facture.getDuration();
        Duration chargedDuration = duration.minusHours(1);

        long nbOfChargedHours = chargedDuration.toHours();

        if (nbOfChargedHours < 0) {
            logger.info("nbOfChargedHours < 0");

            return 0;
        } else if (nbOfChargedHours < 4) {
            logger.info("nbOfChargedHours < 4");
            if (time.isStartedHour(chargedDuration)) {
                logger.info("heure entamée : " + chargedDuration);
                return 2 + nbOfChargedHours * 2;
            } else {
                logger.info("heure non entamée : " + chargedDuration);
                return nbOfChargedHours * 2;
            }
        } else {
            logger.info("nbOfChargedHours >= 4 : " + nbOfChargedHours);

            Duration extraDuration = chargedDuration.minusHours(3);
            long extraMinutes = extraDuration.toMinutes();
            long nbOfChargedHalfHour = extraMinutes / 30;

            long tarifPlancher = 3 * 2;
            if (time.isStarted30minutes(extraDuration)) {
                return tarifPlancher + 1.5 + nbOfChargedHalfHour * 1.5;
            } else {
                return tarifPlancher + nbOfChargedHalfHour * 1.5;
            }
        }
    }

    @Override
    public double appliquerTaux(Facture facture, double tarifHoraire) {
        return tarifHoraire * facture.getVehicule().getTaux() * facture.getCarburant().getTaux();
    }

    @Override
    public double arrondirTarif(double input) {

        double fractionalPart = getFractionalPart(input);
        if (isInteger(input)) {
            logger.debug("input is integer");
            return input;
        } else if (fractionalPart < 0.5) {
            logger.debug("input fractionalPart < 0.5");
            return Math.floor(input) + 0.5;
        } else if (fractionalPart == 0.5) {
            logger.debug("input fractionalPart == 0.5");
            return input;
        } else {
            logger.debug("input fractionalPart > 0.5");
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
