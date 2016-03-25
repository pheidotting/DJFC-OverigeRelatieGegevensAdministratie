package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.inloggen.Sessie;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-unittest.xml")
public class BijlageRepositoryTest {

    @Inject
    private BijlageRepository bijlageRepository;

    @Before
    public void init() {
        Sessie.setIngelogdeGebruiker(46L);
    }

    @Test
    public void opslaan() {
        SoortEntiteit soortEntiteit = SoortEntiteit.SCHADE;
        Long entiteitId = 3L;

        assertEquals(0, bijlageRepository.alles(soortEntiteit, entiteitId).size());

        Bijlage bijlage = new Bijlage();
        bijlage.setEntiteitId(entiteitId);
        bijlage.setSoortEntiteit(soortEntiteit);
        bijlage.setBestandsNaam("aa.pdf");
        bijlage.setOmschrijving("omschr");
        bijlage.setUploadMoment(LocalDateTime.now());

        bijlageRepository.opslaan(newArrayList(bijlage));


        assertEquals(1, bijlageRepository.alles(soortEntiteit, entiteitId).size());
        assertEquals(bijlage, bijlageRepository.lees(bijlage.getId()));

        bijlage.setOmschrijving("Andere bijlage");

        bijlageRepository.opslaan(newArrayList(bijlage));

        assertEquals(1, bijlageRepository.alles(soortEntiteit, entiteitId).size());
        assertEquals(bijlage, bijlageRepository.lees(bijlage.getId()));

        bijlageRepository.verwijder(newArrayList(bijlage));

        assertEquals(0, bijlageRepository.alles(soortEntiteit, entiteitId).size());
    }

}