package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.RekeningNummer;
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
public class RekeningNummerRepositoryTest {

    @Inject
    private RekeningNummerRepository rekeningNummerRepository;

    @Before
    public void init() {
        Sessie.setIngelogdeGebruiker(46L);
    }

    @Test
    public void opslaan() {
        assertEquals(0, rekeningNummerRepository.alles().size());

        RekeningNummer rekeningNummer = new RekeningNummer();
        rekeningNummer.setRekeningnummer("NL12ABCD0123456789");
        rekeningNummer.setEntiteitId(58L);
        rekeningNummer.setSoortEntiteit(SoortEntiteit.RELATIE);

        rekeningNummerRepository.opslaan(rekeningNummer);

        assertEquals(1, rekeningNummerRepository.alles().size());
        assertEquals(rekeningNummer, rekeningNummerRepository.lees(rekeningNummer.getId()));

        rekeningNummer.setBic("bic");

        rekeningNummerRepository.opslaan(rekeningNummer);

        assertEquals(1, rekeningNummerRepository.alles().size());
        assertEquals(rekeningNummer, rekeningNummerRepository.lees(rekeningNummer.getId()));

        rekeningNummerRepository.verwijder(rekeningNummer);

        assertEquals(0, rekeningNummerRepository.alles().size());
    }

}