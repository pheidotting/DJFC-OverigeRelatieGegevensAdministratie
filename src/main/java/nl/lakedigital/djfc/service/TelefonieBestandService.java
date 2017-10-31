package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.TelefonieBestand;
import nl.lakedigital.djfc.repository.TelefonieBestandRepository;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

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
        List<String> result = newArrayList();

        File f = new File(recordingspad);


        for (String s : f.list()) {
            if (!s.contains("anonymous") && !".DS_Store".equals(s)) {
                result.add(s);
            }
        }

        return result;
    }

    public TelefonieBestand maakTelefonieBestand(String file) {
        String[] parts = file.split("-");
        TelefonieBestand telefonieBestand = null;

        if (!"anonymous".equals(parts[1]) && !"ID".equals(parts[1])) {
        try {
            if ("out".equals(parts[0])) {
                //uitgaand bestand
                //            String file = "out-0302780222-2904-20170106-121930-1483701570.658.wav";
                //                String file = "out-030-2780222-2904-20170103-134319-1483447399.315.wav";
                String telefoonnummer;
                String dag;
                String tijd;
                if (parts[1].length() == 3 || parts[1].length() == 4) {
                    telefoonnummer = parts[1] + parts[2];
                    dag = parts[4];
                    tijd = parts[5];
                } else {
                    telefoonnummer = parts[1];
                    dag = parts[3];
                    tijd = parts[4];

                    if (telefoonnummer.startsWith("3") && telefoonnummer.length() == 3) {
                        //0591 vergeten
                        telefoonnummer = "0591" + telefoonnummer;
                    }
                }
                DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYYMMddHHmmss");
                if ((dag + tijd).length() == 14) {
                    LocalDateTime tijdstip = LocalDateTime.parse(dag + tijd, dateTimeFormatter);

                    if (telefoonnummer.length() == 10 || ((telefoonnummer.startsWith("088") || telefoonnummer.startsWith("0800") || telefoonnummer.startsWith("0900")))) {
                        if (telefoonnummer.length() > 10) {
                            telefoonnummer = telefoonnummer.substring(0, 10);
                        }
                        telefonieBestand = new TelefonieBestand(file, telefoonnummer, tijdstip);
                    }
                }
            } else if ("external".equals(parts[0]) || "rg".equals(parts[0])) {
                //                String file = "rg-8001-0561451395-20170113-161919-1484320759.1426.wav";
                String telefoonnummer = parts[2];
                String dag = parts[3];
                String tijd = parts[4];
                DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYYMMddHHmmss");
                if ((dag + tijd).length() == 14) {
                    LocalDateTime tijdstip = LocalDateTime.parse(dag + tijd, dateTimeFormatter);

                    if (telefoonnummer.length() == 10) {
                        telefonieBestand = new TelefonieBestand(file, telefoonnummer, tijdstip);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Fout opgetreden bij parsen bestand {}", file, e);
        }
        }

        //        if (telefonieBestand == null) {
        //            LOGGER.error("Niet kunnen parsen : {}", file);
        //        }
        return telefonieBestand;
    }

    public List<TelefonieBestand> alleTelefonieBestanden() {
        return telefonieBestandRepository.alles();
    }

    public List<TelefonieBestand> alleTelefonieBestandenOpTelefoonnummer(String telefoonnummer) {
        //        String naam = "out-0302780222-2904-20170106-121930-1483701570.658.wav";
        //        int t = 1;
        //        LocalDateTime localDateTime=new LocalDateTime(2016,t,1,t,t);
        //        TelefonieBestand telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 2;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 3;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 4;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 5;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 6;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 7;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 8;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 9;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 10;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 11;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 12;
        //         localDateTime=new LocalDateTime(2016,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 1;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 2;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 3;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 4;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 5;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 6;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 7;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 8;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 9;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 10;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 11;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));
        //         t = 12;
        //         localDateTime=new LocalDateTime(2017,t,1,t,t);
        //         telefonieBestand=new TelefonieBestand(naam,telefoonnummer,localDateTime);
        //        telefonieBestandRepository.opslaan(newArrayList(telefonieBestand));


        return telefonieBestandRepository.allesMetTelefoonnummer(telefoonnummer);
    }

    public void opslaan(List<TelefonieBestand> bestanden) {
        telefonieBestandRepository.opslaan(bestanden);
    }
}
