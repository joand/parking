package fr.joand.parking.pojo;

import lombok.Getter;

public enum CarburantType {
    essence,
    diesel,
    GPL(1.07),
    hybride;

    @Getter
    private final double factor;

    CarburantType() {
        factor = 1d;
    }

    CarburantType(double factor) {
        this.factor = factor;
    }
}
