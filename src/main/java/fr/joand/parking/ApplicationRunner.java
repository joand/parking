package fr.joand.parking;

import fr.joand.parking.core.Facturaion;
import fr.joand.parking.core.Output;
import fr.joand.parking.pojo.CarburantType;
import fr.joand.parking.pojo.Facture;
import fr.joand.parking.pojo.VehiculeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Profile("!test")
@Service
public class ApplicationRunner implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    @Autowired
    private Facturaion facturaion;
    @Autowired
    private Output output;

    @Override
    public void run(String... args) throws Exception {
        logger.info("generate requested files");

        LocalTime debut_1 = LocalTime.of(13, 24);
        LocalTime fin_1 = LocalTime.of(15, 10);
        Facture casUn = facturaion.build(VehiculeType.voiture, CarburantType.essence, debut_1, fin_1);
        facturaion.calculerTarifFinal(casUn);
        output.generate(casUn);

        LocalTime debut_2 = LocalTime.of(19, 30);
        LocalTime fin_2 = LocalTime.of(0, 37);
        Facture casDeux = facturaion.build(VehiculeType.moto, CarburantType.essence, debut_2, fin_2);
        facturaion.calculerTarifFinal(casDeux);
        output.generate(casDeux);

        LocalTime debut_3 = LocalTime.of(7, 43);
        LocalTime fin_3 = LocalTime.of(15, 10);
        Facture casTrois = facturaion.build(VehiculeType.voiture, CarburantType.GPL, debut_3, fin_3);
        facturaion.calculerTarifFinal(casTrois);
        output.generate(casTrois);
    }

}
