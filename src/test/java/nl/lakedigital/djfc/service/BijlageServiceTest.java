package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.BijlageRepository;
import org.easymock.Mock;
import org.easymock.TestSubject;

public class BijlageServiceTest extends AbstractServicetTest<Bijlage> {
    @TestSubject
    private BijlageService bijlageService = new BijlageService();
    @Mock
    private BijlageRepository bijlageRepository;

    @Override
    public AbstractService getService() {
        return bijlageService;
    }

    @Override
    public AbstractRepository getRepository() {
        return bijlageRepository;
    }

    @Override
    public Bijlage getLegeEntiteit() {
        return new Bijlage();
    }

    @Override
    public Bijlage getGevuldeEntiteit() {
        Bijlage bijlage = new Bijlage();
        bijlage.setOmschrijving("Omschr");

        return bijlage;
    }

    @Override
    public Bijlage getGevuldeBestaandeEntiteit() {
        Bijlage bijlage = new Bijlage();
        bijlage.setOmschrijving("Ook een mschr");
        bijlage.setId(3L);

        return bijlage;
    }

    @Override
    public Bijlage getTeVerwijderenEntiteit() {
        Bijlage bijlage = new Bijlage();
        bijlage.setOmschrijving("Deze gaat dus weg");
        bijlage.setId(4L);

        return bijlage;
    }
}
