package fr.joand.parking.core;

import fr.joand.parking.pojo.CarburantType;
import fr.joand.parking.pojo.Facture;
import fr.joand.parking.pojo.VehiculeType;

import java.time.LocalTime;

public interface Facturaion {

    /**
     * The high level method
     */
    double calculerTarifFinal(Facture facture);

    /**
     * the recommended way to build a Facture
     * */
    Facture build(VehiculeType vehicule, CarburantType carburant, LocalTime debut, LocalTime fin);

    double calculerTarifHoraire(Facture facture);

    double appliquerTaux(Facture facture, double tarifHoraire);

    double arrondirTarif(double input);

    double getFractionalPart(double input);

    boolean isInteger(double input);
}
