package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.AbstracteJsonEntiteitMetSoortEnId;
import nl.lakedigital.djfc.domain.AbstracteEntiteitMetSoortEnId;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.mapper.Mapper;
import nl.lakedigital.djfc.service.AbstractService;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.collect.Lists.newArrayList;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;

@RunWith(EasyMockRunner.class)
public abstract class AbstractControllerTest<T extends AbstracteEntiteitMetSoortEnId, U extends AbstracteJsonEntiteitMetSoortEnId> extends EasyMockSupport {
    public abstract AbstractController getController();

    public abstract AbstractService getService();

    public abstract T getEntiteit();

    public abstract U getJsonEntiteit();

    public abstract Class setType();

    public abstract Class setJsonType();

    @Mock
    protected Mapper mapper;
    private Class<T> type;
    private Class<U> jsonType;

    @Before
    public void init() {
        jsonType = setJsonType();
        type = setType();
    }

    @Test
    public void testAlles() {
        SoortEntiteit soortEntiteit = SoortEntiteit.POLIS;
        Long entiteitId = 4L;

        T entiteit = getEntiteit();
        U jsonEntiteit = getJsonEntiteit();

        expect(getService().alles(soortEntiteit, entiteitId)).andReturn(newArrayList(entiteit));
        expect(mapper.map(entiteit, jsonType)).andReturn(jsonEntiteit);

        replayAll();

        assertEquals(newArrayList(jsonEntiteit), getController().alles(soortEntiteit.name(), entiteitId));

        verifyAll();
    }

    @Test
    public void opslaan() {
        SoortEntiteit soortEntiteit = SoortEntiteit.POLIS;
        Long entiteitId = 4L;

        T entiteit = getEntiteit();
        U jsonEntiteit = getJsonEntiteit();
        jsonEntiteit.setSoortEntiteit(soortEntiteit.name());
        jsonEntiteit.setEntiteitId(entiteitId);

        expect(mapper.map(jsonEntiteit, type)).andReturn(entiteit);
        getService().opslaan(newArrayList(entiteit), soortEntiteit, entiteitId);
        expectLastCall();

        replayAll();

        getController().opslaan(newArrayList(jsonEntiteit));

        verifyAll();
    }

    @Test
    public void verwijderen() {
        SoortEntiteit soortEntiteit = SoortEntiteit.POLIS;
        Long entiteitId = 4L;

        getService().verwijderen(soortEntiteit, entiteitId);
        expectLastCall();

        replayAll();

        getController().verwijderen(soortEntiteit.name(), entiteitId);

        verifyAll();
    }

}