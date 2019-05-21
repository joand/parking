package fr.joand.parking.pojo;

import lombok.Data;

import java.time.Duration;

@Data
public class Facture {
    private VehiculeType vehicule;
    private CarburantType carburant;
    private Duration duration;
    private Montant montant;
}
