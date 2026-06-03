package javaapplication3;

public final class ValideringInput {

    private ValideringInput() {
    }

    public enum ArGiltigKostnad {
        JA,
        HAR_OGILTIGA_TECKEN,
        DECIMAL_UTAN_SIFFROR_INNAN,
        DECIMAL_UTAN_SIFFROR_EFTER,
        FOR_LANG_DECIMAL,
        FOR_LANG_INTEGER,
        NEJ
    }

    public enum ArGiltigText {
        JA,
        FOR_LANG,
        OGILTIGT_TECKEN,
        NEJ
    }

    /**
     * Kontrollerar om rollen är handläggare eller ej Fördelen med metoden är
     * att den samlar en "single source of truth" för vad värdet av rollen ska
     * vara för att det ska vara en handläggare. I detta fall antingen
     * "handlaggare" eller "handlaggare_projektchef"
     */
    public static boolean arHandlaggare(String roll) {
        return "handlaggare".equals(roll) || "handlaggare_projektchef".equals(roll);
    }
    
    public static boolean arProjektchef(String roll) {
        return "handlaggare_projektchef".equals(roll);
    }

    /**
     * Kontrollerar om rollen är admin eller ej. Implementera gärna denna metod
     * där egen check för roll.equals "admin" gjorts.
     */
    public static boolean arAdmin(String roll) {
        return "admin".equals(roll);
    }

    /**
     * Returnerar true om kostnaden är giltig, false om inte. Giltiga värden:
     * 000000000000.00 I databasen: Decimal(12, 2)
     *
     * ArGiltigKostnad returnerar olika enums från "ArGiltigKostnad". Dessa
     * berättar resultatet av valideringen. ArGiltigKostnad.JA betyder att
     * kostnaden var korrekt.
     *
     * Hur det returnerade värdet av typ ArGiltigKostnad används i fönstren:
     * if(valideraKostnad(minKostnad) == ArGiltigKostnad.KOMMATECKEN){ //Visa
     * felmeddelande ifall komma tecken användes }
     */
    public static ArGiltigKostnad valideraKostnad(String kostnad) {
        //...trim: Mellanslag på sidorna räkans som giltigt
        kostnad = kostnad.trim();
        boolean tomKostnad = kostnad == null || kostnad.matches(" *");

        //Kollar om kostnaden är giltig
        if (kostnad.matches("^[0-9]{1,12}([.,][0-9]{1,2})?$") || tomKostnad) {
            //Regex förklaring:
            //^  betyder "börjar med det efter"
            //[0-9]{1,12} betyder "mellan 1-12 siffror"
            //([.,][0-9]{1,2})? betyder "med eller utan: en punkt eller komma, följt av 1 eller 2 siffror".
            //$ betyder "slutar med det innan"
            return ArGiltigKostnad.JA; //TESTA
        }

        //Vid ogiltiga värden:
        if (kostnad.matches(".*[^0-9.,].*")) {
            return ArGiltigKostnad.HAR_OGILTIGA_TECKEN;
        } else if (kostnad.matches("^[.,].*")) {
            return ArGiltigKostnad.DECIMAL_UTAN_SIFFROR_INNAN;
        } else if (kostnad.matches(".*[.,]$")) {
            return ArGiltigKostnad.DECIMAL_UTAN_SIFFROR_EFTER;
        } else if (kostnad.matches(".*[.,][0-9]{3,}.*")) {
            return ArGiltigKostnad.FOR_LANG_DECIMAL;
        } else if (kostnad.matches("^[0-9]{13,}.*")) {
            return ArGiltigKostnad.FOR_LANG_INTEGER;
        } else {
            return ArGiltigKostnad.NEJ;
        }
    }

    /**
     * Validera innan normalisering. in: en kostnad ut: en kostnad med: 1.
     * borttagna leading zeroes, 2. punkt istället för komma för decimal och 3.
     * bortagna whitespace karaktärer i början och slut
     */
    public static String normaliseraKostnad(String kostnad) {
        if (kostnad == null || kostnad.matches(" *")) {
            return null; //Om kostnad är null eller bara är whitespace characters
        }
        //Regex förklaring:
        //^0+  alla från början i rad med "greedy matching" - så många som möjligt
        //(?!\\.)  som INTE följs av en punkt precis efter
        //Detta ersätts av "" (tas bort)
        String normaliseradKostnad = kostnad.replaceFirst("^0+(?!\\.)", "")
                .replace(",", ".") //Ersätter "," med "."
                .trim();           //Tar bort mellanslag i början och slut
        return normaliseradKostnad;
    }

    public static ArGiltigText valideraProjektnamn(String projektnamn) {
        projektnamn = projektnamn.trim();
        //Begränsar till vissa normala tecken och till max 100 tecken (efter trim)
        if (projektnamn.matches("^[A-Za-zÅÄÖåäö0-9 _().,\"'!?/><&-]{0,100}$")) {
            return ArGiltigText.JA;
        }

        //Vid ogiltiga värden
        if (projektnamn.length() > 100) {
            return ArGiltigText.FOR_LANG;
        } else if (projektnamn.matches(".*[^A-Za-zÅÄÖåäö0-9 _().,\"'!?/><&-].*")) {
            return ArGiltigText.OGILTIGT_TECKEN;
        } else {
            return ArGiltigText.NEJ;
        }
    }

    /*
    * Validera innan normalisering.
     */
    public static String normaliseraProjektnamn(String projektnamn) {
        boolean tomProjektnamn = projektnamn == null || projektnamn.matches(" *");
        if (tomProjektnamn) {
            return null;
        }

        //Trimma mellanslag på kanterna och anpassar "'" tecken för SQL-inmatning
        projektnamn = projektnamn.trim().replace("'", "''");

        return projektnamn;
    }

    public static ArGiltigText valideraProjektbeskrivning(String beskrivning) {
        beskrivning = beskrivning.trim();
        //Begränsar till vissa normala tecken och till max 250 tecken (efter trim)
        if (beskrivning.matches("^[A-Za-zÅÄÖåäö0-9 _().,\"'!?/><&@#¤%-]{0,250}$")) {
            return ArGiltigText.JA;
        }

        //Vid ogiltiga värden
        if (beskrivning.length() > 250) {
            return ArGiltigText.FOR_LANG;
        } else if (beskrivning.matches(".*[^A-Za-zÅÄÖåäö0-9 _().,\"'!?/><&@#¤%-].*")) {
            return ArGiltigText.OGILTIGT_TECKEN;
        } else {
            return ArGiltigText.NEJ;
        }
    }

    public static String normaliseraProjektbeskrivning(String beskrivning) {
        boolean tomBeskrivning = beskrivning == null || beskrivning.matches(" *");
        if (tomBeskrivning) {
            return null;
        }
        //Trimma mellanslag på kanterna och anpassar "'" tecken för SQL-inmatning
        beskrivning = beskrivning.trim().replace("'", "''");
        
        return beskrivning;
    }

}
