package fr.joand.parking.core;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
class ServiceFacturaion implements Facturaion {

    private Pattern integerPattern = Pattern.compile("^\\d+(\\.0+)?$");

    @Override
    public double arrondi(double input) {

        double fractionalPart = getFractionalPart(input);
        if(fractionalPart < 0.5){
            return Math.floor(input) + 0.5;
        } else if(fractionalPart == 0.5){
            return input;
        } else {
            return Math.ceil(input);
        }
    }

    @Override
    public double getFractionalPart(double input){
        return input % 1;
    }

}
