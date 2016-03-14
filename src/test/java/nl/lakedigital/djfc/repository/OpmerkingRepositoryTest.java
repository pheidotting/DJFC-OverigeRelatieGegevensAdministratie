package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.inloggen.Sessie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-unittest.xml")
public class OpmerkingRepositoryTest {

    @Inject
    private OpmerkingRepository opmerkingRepository;

    @Before
    public void init() {
        Sessie.setIngelogdeGebruiker(46L);
    }

    @Test
    public void opslaan() {
        assertEquals(0, opmerkingRepository.alles().size());

        Opmerking opmerking=new Opmerking();
        opmerking.setEntiteitId(3L);
        opmerking.setSoortEntiteit(SoortEntiteit.POLIS);
        opmerking.setMedewerker(3L);
        opmerking.setOpmerking("blablabla opmerking");

        opmerkingRepository.opslaan(opmerking);

        assertNotNull(opmerking.getTijd());
        assertEquals(1, opmerkingRepository.alles().size());
        assertEquals(opmerking, opmerkingRepository.lees(opmerking.getId()));

        opmerking.setOpmerking("Andere opmerking");

        opmerkingRepository.opslaan(opmerking);

        assertEquals(1, opmerkingRepository.alles().size());
        assertEquals(opmerking, opmerkingRepository.lees(opmerking.getId()));

        opmerkingRepository.verwijder(opmerking);

        assertEquals(0, opmerkingRepository.alles().size());
    }

}