package fr.joand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import fr.joand.parking.Application;
import fr.joand.parking.core.Facturaion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ArrondiTest {

    @Autowired
    private Facturaion facturaion;

    @Test
    public void procheDeUn() {
        double expected = 1.5;
        double actual = facturaion.arrondi(1.01);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void procheDeUnPointCinqDessous(){
        double expected = 1.5;
        double actual = facturaion.arrondi(1.49);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void exactementUnPointCinq(){
        double expected = 1.5;
        double actual = facturaion.arrondi(1.5);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }


    @Test
    public void procheDeUnPointCinqDessus(){
        double expected = 2;
        double actual = facturaion.arrondi(1.51);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }


    @Test
    public void procheDeDeux() {
        double expected = 2;
        double actual = facturaion.arrondi(1.99);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void exactementDeux(){
        double expected = 2;
        double actual = facturaion.arrondi(2);
        double delta = 0.0;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void getFractionalPart(){
        double delta = 0.00001;

        assertEquals(0.1,facturaion.getFractionalPart(0.1), delta);
        assertEquals(0.5, facturaion.getFractionalPart(0.5), delta);
        assertEquals(0.99, facturaion.getFractionalPart(0.99), delta);

        assertEquals(0.1,facturaion.getFractionalPart(1.1), delta);
        assertEquals(0.5, facturaion.getFractionalPart(2.5), delta);
        assertEquals(0.99, facturaion.getFractionalPart(10.99), delta);

        assertEquals(0,facturaion.getFractionalPart(1), delta);
        assertEquals(0, facturaion.getFractionalPart(2), delta);
        assertEquals(0, facturaion.getFractionalPart(10), delta);
    }

    @Test
    public void isInteger(){
        assertTrue(facturaion.isInteger(0.0));
        assertTrue(facturaion.isInteger(0));
        assertTrue(facturaion.isInteger(1));
    }

    @Test
    public void isNotInteger(){
        assertFalse(facturaion.isInteger(0.01));
        assertFalse(facturaion.isInteger(0.5));
        assertFalse(facturaion.isInteger(1.99));
    }
}
