package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.AdresRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AdresService extends AbstractService<Adres> {
    @Inject
    private AdresRepository adresRepository;

    @Override
    public AbstractRepository getRepository() {
        return adresRepository;
    }
}
