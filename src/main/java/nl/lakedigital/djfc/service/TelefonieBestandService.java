package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.TelefonieBestand;
import nl.lakedigital.djfc.repository.TelefonieBestandRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.util.*;

@Service
@Configuration
@PropertySources({@PropertySource("classpath:application.properties"), @PropertySource(value = "file:app.properties", ignoreResourceNotFound = true)})
public class TelefonieBestandService {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${voicemailspad}")
    private String voicemailspad;
    @Value("${recordingspad}")
    private String recordingspad;

    @Inject
    private TelefonieBestandRepository telefonieBestandRepository;

    public List<String> inlezenBestanden() {
        Map<String, List<String>> ret = new HashMap<>();

        File f = new File(recordingspad);
        List<String> recordings = new ArrayList<>(Arrays.asList(f.list()));

        return recordings;
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
