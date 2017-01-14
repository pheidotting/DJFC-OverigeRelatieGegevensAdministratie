package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.TelefonieBestand;

import java.util.List;
import java.util.stream.Collectors;

public class InlezenTelefonieBestandenService implements Runnable {
    private TelefonieBestandService telefonieBestandService;

    public InlezenTelefonieBestandenService(TelefonieBestandService telefonieBestandService) {
        this.telefonieBestandService = telefonieBestandService;
    }

    @Override
    public void run() {
        List<String> bestanden = telefonieBestandService.inlezenBestanden();

        final List<TelefonieBestand> telefonieBestanden = telefonieBestandService.alleTelefonieBestanden();

        List<TelefonieBestand> nieuweBestanden = bestanden.stream().filter(file -> !telefonieBestanden.contains(new TelefonieBestand(file))).map(file -> new TelefonieBestand(file)).collect(Collectors.toList());

        if (!nieuweBestanden.isEmpty()) {
            telefonieBestandService.opslaan(nieuweBestanden);
        }
    }
}
