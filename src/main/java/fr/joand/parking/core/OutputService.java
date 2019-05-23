package fr.joand.parking.core;

import fr.joand.parking.pojo.Facture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
class OutputService implements Output {
    private final Logger logger = LoggerFactory.getLogger(OutputService.class);

    @Autowired
    private Time time;

    @Value("${output.folder}")
    private String outputFolder;

    @Override
    public void generate(Facture facture) {
        String readableDuration = time.format(facture.getDuration());
        String formattedAmount = facture.getMontant().toString().replace(".", ",");

        logger.info("- véhicule : {} {}", facture.getVehicule(), facture.getCarburant());
        logger.info("- temps passé : {}", readableDuration);
        logger.info("- montant dû : {} euros", formattedAmount);

        List<String> lines = Arrays.asList(
                "- véhicule : " + facture.getVehicule() + " " + facture.getCarburant(),
                "- temps passé : " + readableDuration,
                "- montant dû : " + formattedAmount + " euros"
        );

        Path file = Paths.get(outputFolder, facture.toString()+".txt");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.error("Cannot create file {}{}{}", outputFolder, facture.toString(), ".txt");
            logger.error(e.getMessage(), e);
        }
    }
}
