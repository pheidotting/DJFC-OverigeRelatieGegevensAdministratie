package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Telefoonnummer;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.TelefoonnummerRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class TelefoonnummerService extends AbstractService<Telefoonnummer> {
    @Inject
    private TelefoonnummerRepository telefoonnummerRepository;

    @Override
    public AbstractRepository getRepository() {
        return telefoonnummerRepository;
    }
}
