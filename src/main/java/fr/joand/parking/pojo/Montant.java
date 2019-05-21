package fr.joand.parking.pojo;

import lombok.Data;

@Data
public class Montant {
    private double amount;
    private String currency;

    public Montant() {
        amount = 0;
        currency = " euros";
    }
}
