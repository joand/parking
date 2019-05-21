package fr.joand.parking.core;

import fr.joand.parking.pojo.CarburantType;
import fr.joand.parking.pojo.Facture;
import fr.joand.parking.pojo.Montant;
import fr.joand.parking.pojo.VehiculeType;

import java.time.Duration;
import java.time.LocalTime;

public interface Facturaion {

    double calculerTarifHoraire(Facture facture);

    double appliquerTaux(Facture facture, double tarifHoraire);

    /**
     * The high level method
     * */
    double calculerTarifFinal(Facture facture);

    double arrondirTarif(double input);

    double getFractionalPart(double input);

    boolean isInteger(double input);
}
