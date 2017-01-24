package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.AdresRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AdresService extends AbstractService<Adres> {
    @Inject
    private AdresRepository adresRepository;

    @Override
    public AbstractRepository getRepository() {
        return adresRepository;
    }

    @Override
    public void opslaan(Adres adres) {
        super.opslaan(adres);
    }

    @Override
    public void opslaan(final List<Adres> adressen, SoortEntiteit soortEntiteit, Long entiteitId) {
        super.opslaan(adressen, soortEntiteit, entiteitId);
    }

    public void opslaan(final List<Adres> adressen) {
        adresRepository.opslaan(adressen);
    }

    @Override
    public Adres lees(Long id) {
        return adresRepository.lees(id);
    }

    public List<Adres> alles() {
        return adresRepository.alles();
    }

    public void verwijder(List<Adres> adressen) {
        adresRepository.verwijder(adressen);
    }

    public List<Adres> alleAdressenBijLijstMetEntiteiten(List<Long> ids, SoortEntiteit soortEntiteit) {
        return adresRepository.alleAdressenBijLijstMetEntiteiten(ids, soortEntiteit);
    }


}
