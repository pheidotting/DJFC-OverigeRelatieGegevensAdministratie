package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.domain.Telefoonnummer;
import nl.lakedigital.djfc.domain.TelefoonnummerSoort;
import nl.lakedigital.djfc.inloggen.Sessie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-unittest.xml")
public class TelefoonnummerRepositoryTest {

    @Inject
    private TelefoonnummerRepository telefoonnummerRepository;

    @Before
    public void init() {
        Sessie.setIngelogdeGebruiker(46L);
    }

    @Test
    public void opslaan() {
        assertEquals(0, telefoonnummerRepository.alles().size());

        Telefoonnummer telefoonnummer = new Telefoonnummer();
        telefoonnummer.setTelefoonnummer("0612345678");
        telefoonnummer.setEntiteitId(58L);
        telefoonnummer.setSoortEntiteit(SoortEntiteit.CONTACTPERSOON);

        telefoonnummerRepository.opslaan(telefoonnummer);

        assertEquals(1, telefoonnummerRepository.alles().size());
        assertEquals(telefoonnummer, telefoonnummerRepository.lees(telefoonnummer.getId()));

        telefoonnummer.setOmschrijving("jadajada omschrijving");
        telefoonnummer.setSoort(TelefoonnummerSoort.MOBIEL);

        telefoonnummerRepository.opslaan(telefoonnummer);

        assertEquals(1, telefoonnummerRepository.alles().size());
        assertEquals(telefoonnummer, telefoonnummerRepository.lees(telefoonnummer.getId()));

        telefoonnummerRepository.verwijder(telefoonnummer);

        assertEquals(0, telefoonnummerRepository.alles().size());
    }

}