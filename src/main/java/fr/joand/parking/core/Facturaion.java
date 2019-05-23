package fr.joand.parking.core;

import fr.joand.parking.pojo.Facture;

public interface Facturaion {

    /**
     * The high level method
     * */
    double calculerTarifFinal(Facture facture);

    double calculerTarifHoraire(Facture facture);

    double appliquerTaux(Facture facture, double tarifHoraire);

    double arrondirTarif(double input);

    double getFractionalPart(double input);

    boolean isInteger(double input);
}
