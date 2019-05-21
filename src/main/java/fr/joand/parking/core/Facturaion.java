package fr.joand.parking.core;

import fr.joand.parking.pojo.CarburantType;
import fr.joand.parking.pojo.VehiculeType;

import java.time.Duration;
import java.time.LocalTime;

public interface Facturaion {

    double calculerTarif(LocalTime debut, LocalTime fin, VehiculeType vehiculeType, CarburantType carburantType);

    double calculerTarifArrondi(LocalTime debut, LocalTime fin, VehiculeType vehiculeType, CarburantType carburantType);

    double arrondirTarif(double input);

    double getFractionalPart(double input);

    boolean isInteger(double input);
}
