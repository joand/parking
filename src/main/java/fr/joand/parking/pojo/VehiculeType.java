package fr.joand.parking.pojo;

import lombok.Getter;

public enum VehiculeType {
    voiture,
    moto(0.5),
    camion;

    @Getter
    private final double factor;

    VehiculeType() {
        factor = 1d;
    }

    VehiculeType(double factor) {
        this.factor = factor;
    }
}
