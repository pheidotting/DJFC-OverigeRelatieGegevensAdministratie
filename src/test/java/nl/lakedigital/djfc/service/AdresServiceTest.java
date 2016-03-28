package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.messaging.sender.AdresOpgeslagenTaakSender;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.AdresRepository;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

public class AdresServiceTest extends AbstractServicetTest<Adres> {
    @TestSubject
    private AdresService adresService = new AdresService();
    @Mock
    private AdresRepository adresRepository;
    @Mock
    private AdresOpgeslagenTaakSender adresOpgeslagenTaakSender;

    @Override
    public AbstractService getService() {
        return adresService;
    }

    @Override
    public AbstractRepository getRepository() {
        return adresRepository;
    }

    @Override
    public Adres getLegeEntiteit() {
        return new Adres();
    }

    @Override
    public Adres getGevuldeEntiteit() {
        Adres adres = new Adres();
        adres.setPostcode("1234AA");

        return adres;
    }

    @Override
    public Adres getGevuldeBestaandeEntiteit() {
        Adres bestaand = new Adres();
        bestaand.setId(1L);
        bestaand.setPostcode("1234AA");

        return bestaand;
    }

    @Override
    public Adres getTeVerwijderenEntiteit() {
        Adres teVerwijderen = new Adres();
        teVerwijderen.setId(2L);
        teVerwijderen.setPostcode("2345BB");

        return teVerwijderen;
    }

    @Override
    public void testOpslaan() throws Exception {
        adresOpgeslagenTaakSender.send(getLegeEntiteit());
        expectLastCall();

        super.testOpslaan();
    }

    @Override
    public void testOpslaanLijst() throws Exception {
        adresOpgeslagenTaakSender.send(getGevuldeEntiteit());
        expectLastCall();
        adresOpgeslagenTaakSender.send(getGevuldeBestaandeEntiteit());
        expectLastCall();

        super.testOpslaanLijst();
    }

    @Test
    public void testLees() {
        Adres adres = new Adres();
        Long id = 5L;

        expect(getRepository().lees(id)).andReturn(adres);

        replayAll();

        getService().lees(id);

        verifyAll();
    }
}