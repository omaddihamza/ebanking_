package net.me.ebanking.utils;

import java.util.UUID;

public class AccountNumber {
    public static String generateAccountNumber() {
        // Générer un UUID et extraire uniquement les chiffres
        String numericUUID = UUID.randomUUID().toString().replaceAll("\\D", "");

        // Assurer que le numéro a exactement 24 chiffres
        return (numericUUID + "000000000000000000000000").substring(0, 24);
    }
}
