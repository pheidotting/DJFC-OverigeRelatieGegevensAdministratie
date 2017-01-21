package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.TelefonieBestand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

public class InlezenTelefonieBestandenService implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(InlezenTelefonieBestandenService.class);
    private TelefonieBestandService telefonieBestandService;

    public InlezenTelefonieBestandenService(TelefonieBestandService telefonieBestandService) {
        this.telefonieBestandService = telefonieBestandService;
    }

    public InlezenTelefonieBestandenService() {
        //Omdat SonarQube anders zeurt
    }

    @Override
    public void run() {
        LOGGER.debug("Inlezen telefoniebestanden");

        List<String> bestanden = telefonieBestandService.inlezenBestanden();

        final List<TelefonieBestand> telefonieBestanden = telefonieBestandService.alleTelefonieBestanden();

        LOGGER.debug("Gevonden : {} bestanden", bestanden.size());
        LOGGER.debug("Al bestaand : {} bestanden", telefonieBestanden.size());

        List<String> nieuweBestandenString = bestanden.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String file) {
                LOGGER.debug("{} : {} ", file, !telefonieBestanden.contains(new TelefonieBestand(file)));
                return !telefonieBestanden.contains(new TelefonieBestand(file));
            }
        }).collect(Collectors.toList());

        List<TelefonieBestand> nieuweBestanden = newArrayList();
        for (String s : nieuweBestandenString) {
            LOGGER.debug(s);
            nieuweBestanden.add(new TelefonieBestand(s));
            LOGGER.debug("Lengte {}", nieuweBestanden.size());
        }

        //        .map(new Function<String, TelefonieBestand>() {
        //            @Override
        //            public TelefonieBestand apply(String file) {
        //                TelefonieBestand tb = new TelefonieBestand(file);
        //
        //                LOGGER.debug(ReflectionToStringBuilder.toString(tb));
        //
        //                return tb;
        //            }
        //        }).collect(Collectors.toList());

        LOGGER.debug("a");
        LOGGER.debug("{} nieuwe bestanden", nieuweBestanden.size());

        if (!nieuweBestanden.isEmpty()) {
            LOGGER.debug("Opslaan {} nieuwe bestanden", nieuweBestanden.size());
            for (TelefonieBestand telefonieBestand : nieuweBestanden) {
                LOGGER.debug(telefonieBestand.getBestandsnaam());
            }

            telefonieBestandService.opslaan(nieuweBestanden);
        }
    }
}
