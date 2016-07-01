package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.domain.GroepBijlages;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.BijlageRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BijlageService extends AbstractService<Bijlage> {
    @Inject
    private BijlageRepository bijlageRepository;

    @Override
    public AbstractRepository getRepository() {
        return bijlageRepository;
    }

    public void verwijder(Long id) {
        bijlageRepository.verwijder(bijlageRepository.lees(id));
    }

    public GroepBijlages leesGroepBijlages(Long id) {
        return bijlageRepository.leesGroepBijlages(id);
    }

    public List<GroepBijlages> alleGroepenBijlages(SoortEntiteit soortEntiteit, Long entiteitId) {
        return bijlageRepository.alleGroepenBijlages(soortEntiteit, entiteitId);
    }
}