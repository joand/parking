package fr.joand.parking.pojo;

import lombok.Getter;

public enum VehiculeType {
    voiture,
    moto(0.5),
    camion;

    @Getter
    private final double taux;

    VehiculeType() {
        taux = 1d;
    }

    VehiculeType(double taux) {
        this.taux = taux;
    }
}
