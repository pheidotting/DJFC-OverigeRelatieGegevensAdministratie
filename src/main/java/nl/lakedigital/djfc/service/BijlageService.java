package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.AdresRepository;
import nl.lakedigital.djfc.repository.BijlageRepository;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class BijlageService extends AbstractService<Bijlage> {
    public BijlageService() {
        super(Bijlage.class);
    }

    @Inject
    private BijlageRepository bijlageRepository;

    @Override
    public AbstractRepository getRepository() {
        return bijlageRepository;
    }
}