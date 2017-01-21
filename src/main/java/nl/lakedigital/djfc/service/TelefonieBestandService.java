package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.TelefonieBestand;
import nl.lakedigital.djfc.repository.TelefonieBestandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Configuration
@PropertySources({@PropertySource("classpath:application.properties"), @PropertySource(value = "file:app.properties", ignoreResourceNotFound = true)})
public class TelefonieBestandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelefonieBestandService.class);

    @Value("${voicemailspad}")
    private String voicemailspad;
    @Value("${recordingspad}")
    private String recordingspad;

    @Inject
    private TelefonieBestandRepository telefonieBestandRepository;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public List<String> inlezenBestanden() {
        LOGGER.debug("Inlezen bestanden vanaf {}", recordingspad);
        File f = new File(recordingspad);

        LOGGER.debug(f.toString());

        for (String s : f.list()) {
            LOGGER.debug(s);
        }

        LOGGER.debug("Einde lijst");

        return new ArrayList<>(Arrays.asList(f.list()));
    }

    public List<TelefonieBestand> alleTelefonieBestanden() {
        return telefonieBestandRepository.alles();
    }

    public List<TelefonieBestand> alleTelefonieBestandenOpTelefoonnummer(String telefoonnummer) {
        return telefonieBestandRepository.allesMetTelefoonnummer(telefoonnummer);
    }

    public void opslaan(List<TelefonieBestand> bestanden) {
        telefonieBestandRepository.opslaan(bestanden);
    }
}
