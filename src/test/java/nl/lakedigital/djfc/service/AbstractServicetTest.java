package nl.lakedigital.djfc.service;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.domain.AbstracteEntiteitMetSoortEnId;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.repository.AbstractRepository;
import nl.lakedigital.djfc.repository.AdresRepository;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

@RunWith(EasyMockRunner.class)
public abstract class AbstractServicetTest<T extends AbstracteEntiteitMetSoortEnId> extends EasyMockSupport {
    public abstract AbstractService getService();

    public abstract AbstractRepository getRepository();

    public abstract T getLegeEntiteit();

    public abstract T getGevuldeEntiteit();

    public abstract T getGevuldeBestaandeEntiteit();

    public abstract T getTeVerwijderenEntiteit();

    @Test
    public void testAlles() throws Exception {
        List<T> lijst = new ArrayList<>();

        expect(getRepository().alles(SoortEntiteit.SCHADE, 3L)).andReturn(lijst);

        replayAll();

        assertEquals(lijst, getService().alles(SoortEntiteit.SCHADE, 3L));

        verifyAll();
    }

    @Test
    public void testLees() throws Exception {
        T entiteit = getLegeEntiteit();
        Long id = 33L;

        expect(getRepository().lees(id)).andReturn(entiteit);

        replayAll();

        assertEquals(entiteit, getService().lees(id));

        verifyAll();
    }

    @Test
    public void testOpslaan() throws Exception {
        T entiteit = getLegeEntiteit();

        getRepository().opslaan(Lists.newArrayList(entiteit));
        if (entiteit instanceof Adres) {
            expectLastCall().andDelegateTo(new AdresRepository() {
                @Override
                public void opslaan(List<Adres> adressen) {
                    adressen.get(0).setId(46L);
                }
            });
        } else {
            expectLastCall();
        }

        replayAll();

        getService().opslaan(entiteit);

        verifyAll();
    }

    @Test
    public void testOpslaanLijst() throws Exception {
        SoortEntiteit soortEntiteit = SoortEntiteit.POLIS;
        Long entiteitId = 55L;

        T nieuw = getGevuldeEntiteit();
        T bestaand = getGevuldeBestaandeEntiteit();
        T teVerwijderen = getTeVerwijderenEntiteit();

        expect(getRepository().alles(soortEntiteit, entiteitId)).andReturn(Lists.newArrayList(bestaand, teVerwijderen));

        getRepository().opslaan(Lists.newArrayList(nieuw, bestaand));
        if (nieuw instanceof Adres) {
            expectLastCall().andDelegateTo(new AdresRepository() {
                private Long id = 41L;

                @Override
                public void opslaan(List<Adres> adres) {
                    adres.get(0).setId(id++);
                }
            });
        } else {
            expectLastCall();
        }
        getRepository().verwijder(Lists.newArrayList(teVerwijderen));
        expectLastCall();

        replayAll();

        getService().opslaan(Lists.newArrayList(nieuw, bestaand), soortEntiteit, entiteitId);

        verifyAll();
    }

    @Test
    public void testVerwijderen() throws Exception {
        SoortEntiteit soortEntiteit = SoortEntiteit.POLIS;
        Long entiteitId = 55L;

        T entiteit = getLegeEntiteit();
        List<T> entiteiten = Lists.newArrayList(entiteit);

        expect(getRepository().alles(soortEntiteit, entiteitId)).andReturn(entiteiten);
        getRepository().verwijder(Lists.newArrayList(entiteit));
        expectLastCall();

        replayAll();

        getService().verwijderen(soortEntiteit, entiteitId);

        verifyAll();
    }
}
