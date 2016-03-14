package nl.lakedigital.djfc.service;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.repository.AdresRepository;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.*;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(EasyMockRunner.class)
public class AdresServiceTest extends EasyMockSupport {
    @TestSubject
    private AdresService adresService=new AdresService();
    @Mock
    private AdresRepository adresRepository;

    @Test
    public void testAlles() throws Exception {
        List<Adres> adresList=new ArrayList<>();

        expect(adresRepository.alles(SoortEntiteit.SCHADE,3L)).andReturn(adresList);

        replayAll();

        assertEquals(adresList,adresService.alles(SoortEntiteit.SCHADE,3L));

        verifyAll();
    }

    @Test
    public void testLees() throws Exception {
        Adres adres=new Adres();
        Long id = 33L;

        expect(adresRepository.lees(id)).andReturn(adres);

        replayAll();

        assertEquals(adres,adresService.lees(id));

        verifyAll();
    }

    @Test
    public void testOpslaan() throws Exception {
        Adres adres=new Adres();

        adresRepository.opslaan(adres);
        expectLastCall();

        replayAll();

        adresService.opslaan(adres);

        verifyAll();
    }

    @Test
    public void testOpslaanLijst() throws Exception {
        SoortEntiteit soortEntiteit=SoortEntiteit.POLIS;
        Long entiteitId = 55L;

        Adres nieuw=new Adres();
        nieuw.setPostcode("3456CC");
        Adres bestaand = new Adres();
        bestaand.setId(1L);
        bestaand.setPostcode("1234AA");
        Adres teVerwijderen = new Adres();
        teVerwijderen.setId(2L);
        teVerwijderen.setPostcode("2345BB");

        expect(adresRepository.alles(soortEntiteit,entiteitId)).andReturn(Lists.newArrayList(bestaand,teVerwijderen));

        adresRepository.opslaan(bestaand);
        adresRepository.opslaan(nieuw);
        adresRepository.verwijder(teVerwijderen);

        replayAll();

        adresService.opslaan(Lists.newArrayList(nieuw,bestaand),soortEntiteit,entiteitId);

        verifyAll();
    }

    @Test
    public void testVerwijderen() throws Exception {
        SoortEntiteit soortEntiteit=SoortEntiteit.POLIS;
        Long entiteitId = 55L;

        Adres adres=new Adres();
        List<Adres> adressen= Lists.newArrayList(adres);

        expect(adresRepository.alles(soortEntiteit,entiteitId)).andReturn(adressen);
        adresRepository.verwijder(adres);
        expectLastCall();

        replayAll();

        adresService.verwijderen(soortEntiteit,entiteitId);

        verifyAll();
    }
}