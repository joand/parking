package fr.joand.parking.pojo;

import lombok.Getter;

public enum CarburantType {
    essence,
    diesel,
    GPL(1.07),
    hybride;

    @Getter
    private final double taux;

    CarburantType() {
        taux = 1d;
    }

    CarburantType(double taux) {
        this.taux = taux;
    }
}
