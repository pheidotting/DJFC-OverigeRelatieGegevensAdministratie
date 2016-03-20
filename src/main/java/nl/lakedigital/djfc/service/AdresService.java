package nl.lakedigital.djfc.service;

import com.google.common.base.Predicate;

import static com.google.common.collect.Iterables.filter;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.AdresRepository;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AdresService extends AbstractService<Adres> {
    public AdresService() {
        super(Adres.class);
    }

    @Inject
    private AdresRepository adresRepository;

    @Override
    public AbstractRepository getRepository() {
        return adresRepository;
    }
}
