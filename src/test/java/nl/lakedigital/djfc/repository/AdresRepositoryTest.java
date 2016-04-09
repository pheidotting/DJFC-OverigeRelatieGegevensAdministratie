package nl.lakedigital.djfc.repository;

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
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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

        adresRepository.opslaan(newArrayList(adres));

        assertEquals(1, adresRepository.alles(soortEntiteit, relatieId).size());
        assertEquals(0, adresRepository.alles(SoortEntiteit.POLIS, relatieId).size());
        assertEquals(0, adresRepository.alles(soortEntiteit, relatieId + 1).size());
        assertEquals(adres, adresRepository.lees(adres.getId()));

        adres.setHuisnummer(333L);
        adres.setPlaats("Gotham");
        adres.setSoortAdres(Adres.SoortAdres.POSTADRES);

        adresRepository.opslaan(newArrayList(adres));

        assertEquals(1, adresRepository.alles(soortEntiteit, relatieId).size());
        assertEquals(adres, adresRepository.lees(adres.getId()));

        adresRepository.verwijder(newArrayList(adres));

        assertEquals(0, adresRepository.alles(soortEntiteit, relatieId).size());
    }

    @Test
    public void zoeken() {
        Adres adres1 = new Adres();
        adres1.setStraat("abc");
        adres1.setPlaats("bcd");

        Adres adres2 = new Adres();
        adres2.setStraat("cde");
        adres2.setPlaats("efg");

        List<Adres> adressen = newArrayList(adres1, adres2);

        adresRepository.opslaan(adressen);

        assertThat(adresRepository.zoek("a").size(), is(1));
        assertThat(adresRepository.zoek("a").get(0), is(adres1));
        assertThat(adresRepository.zoek("b").size(), is(1));
        assertThat(adresRepository.zoek("b").get(0), is(adres1));
        assertThat(adresRepository.zoek("c").size(), is(2));
        assertThat(adresRepository.zoek("c").contains(adres1), is(true));
        assertThat(adresRepository.zoek("c").contains(adres2), is(true));
        assertThat(adresRepository.zoek("d").size(), is(2));
        assertThat(adresRepository.zoek("d").contains(adres1), is(true));
        assertThat(adresRepository.zoek("d").contains(adres2), is(true));
        assertThat(adresRepository.zoek("e").size(), is(1));
        assertThat(adresRepository.zoek("e").get(0), is(adres2));
        assertThat(adresRepository.zoek("f").size(), is(1));
        assertThat(adresRepository.zoek("f").get(0), is(adres2));
        assertThat(adresRepository.zoek("g").size(), is(1));
        assertThat(adresRepository.zoek("g").get(0), is(adres2));

        adresRepository.verwijder(adressen);
    }
}