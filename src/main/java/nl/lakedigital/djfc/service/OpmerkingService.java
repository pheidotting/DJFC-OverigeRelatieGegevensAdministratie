package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.OpmerkingRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class OpmerkingService extends AbstractService<Opmerking> {
    @Inject
    private OpmerkingRepository opmerkingRepository;

    @Override
    public AbstractRepository getRepository() {
        return opmerkingRepository;
    }
}