package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonBijlage;
import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.BijlageService;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BijlageControllerTest extends AbstractControllerTest<Bijlage, JsonBijlage> {
    @TestSubject
    private BijlageController bijlageController = new BijlageController();
    @Mock
    private BijlageService bijlageService;

    @Override
    public AbstractController getController() {
        return bijlageController;
    }

    @Override
    public AbstractService getService() {
        return bijlageService;
    }

    @Override
    public Bijlage getEntiteit() {
        return new Bijlage();
    }

    @Override
    public JsonBijlage getJsonEntiteit() {
        return new JsonBijlage();
    }

    @Override
    public Class setType() {
        return Bijlage.class;
    }

    @Override
    public Class setJsonType() {
        return JsonBijlage.class;
    }

    @Test
    public void testVerwijder() {
        Long id = 7L;

        HttpServletRequest httpServletRequest = createMock(HttpServletRequest.class);
        expect(httpServletRequest.getHeader("ingelogdeGebruiker")).andReturn("46");
        expect(httpServletRequest.getHeader("trackAndTraceId")).andReturn("trackAndTraceId");

        bijlageService.verwijder(id);
        expectLastCall();

        replayAll();

        bijlageController.verwijder(id, httpServletRequest);

        verifyAll();
    }

    @Test
    public void testOpslaan() {
        JsonBijlage jsonBijlage = new JsonBijlage();
        final Bijlage bijlage = new Bijlage();
        final Long id = 9L;
        HttpServletRequest httpServletRequest = createMock(HttpServletRequest.class);
        expect(httpServletRequest.getHeader("ingelogdeGebruiker")).andReturn("46");
        expect(httpServletRequest.getHeader("trackAndTraceId")).andReturn("trackAndTraceId");

        expect(mapper.map(jsonBijlage, Bijlage.class)).andReturn(bijlage);
        bijlageService.opslaan(bijlage);
        expectLastCall().andDelegateTo(new BijlageService() {
            @Override
            public void opslaan(Bijlage bijlage1) {
                bijlage1.setId(id);
            }
        });

        replayAll();

        assertThat(bijlageController.opslaan(jsonBijlage, httpServletRequest), is(id));

        verifyAll();

    }
}