package fr.joand.parking.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import fr.joand.parking.Application;
import fr.joand.parking.pojo.CarburantType;
import fr.joand.parking.pojo.Facture;
import fr.joand.parking.pojo.VehiculeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FacturaionTest {

    @Autowired
    private Facturaion facturaion;

    @Test
    public void procheDeUn() {
        double expected = 1.5;
        double actual = facturaion.arrondirTarif(1.01);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void procheDeUnPointCinqDessous() {
        double expected = 1.5;
        double actual = facturaion.arrondirTarif(1.49);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void exactementUnPointCinq() {
        double expected = 1.5;
        double actual = facturaion.arrondirTarif(1.5);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }


    @Test
    public void procheDeUnPointCinqDessus() {
        double expected = 2;
        double actual = facturaion.arrondirTarif(1.51);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }


    @Test
    public void procheDeDeux() {
        double expected = 2;
        double actual = facturaion.arrondirTarif(1.99);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void exactementDeux() {
        double expected = 2;
        double actual = facturaion.arrondirTarif(2);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getFractionalPart() {
        double delta = 0.00001;

        assertEquals(0.1, facturaion.getFractionalPart(0.1), delta);
        assertEquals(0.5, facturaion.getFractionalPart(0.5), delta);
        assertEquals(0.99, facturaion.getFractionalPart(0.99), delta);

        assertEquals(0.1, facturaion.getFractionalPart(1.1), delta);
        assertEquals(0.5, facturaion.getFractionalPart(2.5), delta);
        assertEquals(0.99, facturaion.getFractionalPart(10.99), delta);

        assertEquals(0, facturaion.getFractionalPart(1), delta);
        assertEquals(0, facturaion.getFractionalPart(2), delta);
        assertEquals(0, facturaion.getFractionalPart(10), delta);
    }

    @Test
    public void isInteger() {
        assertTrue(facturaion.isInteger(0.0));
        assertTrue(facturaion.isInteger(0));
        assertTrue(facturaion.isInteger(1));
    }

    @Test
    public void isNotInteger() {
        assertFalse(facturaion.isInteger(0.01));
        assertFalse(facturaion.isInteger(0.5));
        assertFalse(facturaion.isInteger(1.99));
    }


    @Test
    public void premiereHeureGratuite() {
        double expected = 0;

        LocalTime debut = LocalTime.of(0, 0);
        LocalTime fin = LocalTime.of(0, 10);
        Duration duration = Duration.between(debut,fin);

        Facture facture = new Facture(VehiculeType.voiture, CarburantType.essence,duration);

        double actual = facturaion.calculerTarifHoraire(facture);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    /**
     * de minuit à 4h ça fait = 1h gratuite + 3h à 2€/h ?
     * */
    @Test
    public void avantQuatreHeures() {
        double expected = 6;

        LocalTime debut = LocalTime.of(0, 0);
        LocalTime fin = LocalTime.of(4, 0);
        Duration duration = Duration.between(debut,fin);

        Facture facture = new Facture(VehiculeType.voiture, CarburantType.essence, duration);

        double actual = facturaion.calculerTarifHoraire(facture);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }


    /**
     * de minuit à 4h ça fait = 1h gratuite + 3h à 2€/h ?
     * */
    @Test
    public void avantQuatreHeuresAvecHeureEntamee() {
        double expected = 8;

        LocalTime debut = LocalTime.of(0, 0);
        LocalTime fin = LocalTime.of(4, 1);
        Duration duration = Duration.between(debut,fin);

        Facture facture = new Facture(VehiculeType.voiture, CarburantType.essence, duration);

        double actual = facturaion.calculerTarifHoraire(facture);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    /**
     * de minuit à 6h10 ça fait =
     * 1h gratuite
     * + 4h à 2€/h ?
     * + 1h10 à 1.5/30 minutes ?
     * */
    @Test
    public void apresQuatreHeures() {
        double expected = 4 * 2 + 3 * 1.5;

        LocalTime debut = LocalTime.of(0, 0);
        LocalTime fin = LocalTime.of(6, 10);
        Duration duration = Duration.between(debut,fin);

        Facture facture = new Facture(VehiculeType.voiture, CarburantType.essence, duration);

        double actual = facturaion.calculerTarifHoraire(facture);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void casUn() {
        double expected = 2;

        LocalTime debut = LocalTime.of(13, 24);
        LocalTime fin = LocalTime.of(15, 10);
        Duration duration = Duration.between(debut,fin);

        Facture facture = new Facture(VehiculeType.voiture, CarburantType.essence, duration);

        double actual = facturaion.calculerTarifFinal(facture);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void casDeux() {
        double expected = 5.5;

        LocalTime debut = LocalTime.of(19, 30);
        LocalTime fin = LocalTime.of(0, 37);
        Duration duration = Duration.between(debut,fin);

        Facture facture = new Facture(VehiculeType.moto, CarburantType.essence, duration);

        double actual = facturaion.calculerTarifFinal(facture);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void casTrois() {
        double expected = 18;

        LocalTime debut = LocalTime.of(7, 43);
        LocalTime fin = LocalTime.of(15, 10);
        Duration duration = Duration.between(debut,fin);

        Facture facture = new Facture(VehiculeType.voiture, CarburantType.GPL, duration);

        double actual = facturaion.calculerTarifFinal(facture);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }
}
