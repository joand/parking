package fr.joand.parking.pojo;

import lombok.Data;

import java.time.Period;

@Data
public class Facture {
    private VehiculeType vehicule;
    private CarburantType carburant;
    private Period period;
    private Montant montant;
}
