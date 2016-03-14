package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
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
public class AdresRepositoryTest {
    private Long relatieId = 58L;
    private SoortEntiteit soortEntiteit=SoortEntiteit.RELATIE;

    @Inject
    private AdresRepository adresRepository;

    @Before
    public void init() {
        Sessie.setIngelogdeGebruiker(46L);
    }

    @Test
    public void opslaan() {
        assertEquals(0, adresRepository.alles(soortEntiteit,relatieId).size());

        Adres adres = new Adres();
        adres.setEntiteitId(relatieId);
        adres.setSoortEntiteit(SoortEntiteit.RELATIE);
        adres.setPostcode("1234AA");

        adresRepository.opslaan(adres);

        assertEquals(1, adresRepository.alles(soortEntiteit,relatieId).size());
        assertEquals(0, adresRepository.alles(SoortEntiteit.POLIS,relatieId).size());
        assertEquals(0, adresRepository.alles(soortEntiteit,relatieId+1).size());
        assertEquals(adres, adresRepository.lees(adres.getId()));

        adres.setHuisnummer(333L);
        adres.setPlaats("Gotham");
        adres.setSoortAdres(Adres.SoortAdres.POSTADRES);

        adresRepository.opslaan(adres);

        assertEquals(1, adresRepository.alles(soortEntiteit,relatieId).size());
        assertEquals(adres, adresRepository.lees(adres.getId()));

        adresRepository.verwijder(adres);

        assertEquals(0, adresRepository.alles(soortEntiteit,relatieId).size());
    }

}