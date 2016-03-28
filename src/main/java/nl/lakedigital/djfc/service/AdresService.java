package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.messaging.sender.AdresOpgeslagenTaakSender;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.AdresRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AdresService extends AbstractService<Adres> {
    @Inject
    private AdresRepository adresRepository;
    @Inject
    private AdresOpgeslagenTaakSender adresOpgeslagenTaakSender;

    @Override
    public AbstractRepository getRepository() {
        return adresRepository;
    }

    @Override
    public void opslaan(Adres adres) {
        super.opslaan(adres);

        if (!adres.isCompleet()) {
            adresOpgeslagenTaakSender.send(adres);
        }
    }

    @Override
    public void opslaan(final List<Adres> adressen, SoortEntiteit soortEntiteit, Long entiteitId) {
        super.opslaan(adressen, soortEntiteit, entiteitId);

        for (Adres adres : adressen) {
            if (!adres.isCompleet()) {
                adresOpgeslagenTaakSender.send(adres);
            }
        }
    }

    public Adres lees(Long id) {
        return adresRepository.lees(id);
    }
}
