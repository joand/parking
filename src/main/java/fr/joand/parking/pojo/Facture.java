package fr.joand.parking.pojo;

import lombok.Data;

import java.time.Duration;

@Data
public class Facture {
    private final VehiculeType vehicule;
    private final CarburantType carburant;
    private final Duration duration;
}
