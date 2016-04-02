package nl.lakedigital.djfc.repository;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.inloggen.Sessie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-unittest.xml")
public class AdresRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);

    private Long relatieId = 58L;
    private SoortEntiteit soortEntiteit = SoortEntiteit.RELATIE;

    @Inject
    private AdresRepository adresRepository;

    @Before
    public void init() {
        Sessie.setIngelogdeGebruiker(46L);
    }

    @Test
    public void opslaan() {
        LOGGER.info("moije");

        assertEquals(0, adresRepository.alles(soortEntiteit, relatieId).size());

        Adres adres = new Adres();
        adres.setEntiteitId(relatieId);
        adres.setSoortEntiteit(SoortEntiteit.RELATIE);
        adres.setPostcode("1234AA");

        adresRepository.opslaan(Lists.newArrayList(adres));

        assertEquals(1, adresRepository.alles(soortEntiteit, relatieId).size());
        assertEquals(0, adresRepository.alles(SoortEntiteit.POLIS, relatieId).size());
        assertEquals(0, adresRepository.alles(soortEntiteit, relatieId + 1).size());
        assertEquals(adres, adresRepository.lees(adres.getId()));

        adres.setHuisnummer(333L);
        adres.setPlaats("Gotham");
        adres.setSoortAdres(Adres.SoortAdres.POSTADRES);

        adresRepository.opslaan(Lists.newArrayList(adres));

        assertEquals(1, adresRepository.alles(soortEntiteit, relatieId).size());
        assertEquals(adres, adresRepository.lees(adres.getId()));

        adresRepository.verwijder(Lists.newArrayList(adres));

        assertEquals(0, adresRepository.alles(soortEntiteit, relatieId).size());
    }

}