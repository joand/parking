package fr.joand.parking.core;

import fr.joand.parking.pojo.Facture;

public interface Output {
    void generate(Facture facture);
}
