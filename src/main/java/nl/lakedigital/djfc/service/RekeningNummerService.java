package nl.lakedigital.djfc.service;

import com.google.common.base.Predicate;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.RekeningNummer;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.AdresRepository;
import nl.lakedigital.djfc.repository.RekeningNummerRepository;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class RekeningNummerService extends AbstractService<RekeningNummer> {
    public RekeningNummerService() {
        super(RekeningNummer.class);
    }

    @Inject
    private RekeningNummerRepository rekeningNummerRepository;

    @Override
    public AbstractRepository getRepository() {
        return rekeningNummerRepository;
    }
}
