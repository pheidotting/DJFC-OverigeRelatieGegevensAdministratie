package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.OpmerkingRepository;
import org.easymock.Mock;
import org.easymock.TestSubject;

public class OpmerkingServiceTest extends AbstractServicetTest<Opmerking> {
    @TestSubject
    public OpmerkingService opmerkingService = new OpmerkingService();
    @Mock
    private OpmerkingRepository opmerkingRepository;

    @Override
    public AbstractService getService() {
        return opmerkingService;
    }

    @Override
    public AbstractRepository getRepository() {
        return opmerkingRepository;
    }

    @Override
    public Opmerking getLegeEntiteit() {
        return new Opmerking();
    }

    @Override
    public Opmerking getGevuldeEntiteit() {
        Opmerking opmerking = new Opmerking();
        opmerking.setOpmerking("opm1");

        return opmerking;
    }

    @Override
    public Opmerking getGevuldeBestaandeEntiteit() {
        Opmerking opmerking = new Opmerking();
        opmerking.setOpmerking("opm2");
        opmerking.setId(2L);

        return opmerking;
    }

    @Override
    public Opmerking getTeVerwijderenEntiteit() {
        Opmerking opmerking = new Opmerking();
        opmerking.setOpmerking("opm3");
        opmerking.setId(3L);

        return opmerking;
    }
}