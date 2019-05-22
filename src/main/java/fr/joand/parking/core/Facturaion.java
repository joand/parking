package fr.joand.parking.core;

import fr.joand.parking.pojo.Facture;

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
