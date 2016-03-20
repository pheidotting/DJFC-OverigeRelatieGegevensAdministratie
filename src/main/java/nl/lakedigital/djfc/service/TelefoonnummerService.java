package nl.lakedigital.djfc.service;

import com.google.common.base.Predicate;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.Telefoonnummer;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.AdresRepository;
import nl.lakedigital.djfc.repository.TelefoonnummerRepository;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;


@Service
public class TelefoonnummerService extends AbstractService<Telefoonnummer> {
        public TelefoonnummerService() {
            super(Telefoonnummer.class);
        }

        @Inject
        private TelefoonnummerRepository telefoonnummerRepository;

        @Override
        public AbstractRepository getRepository() {
            return telefoonnummerRepository;
        }
    }
