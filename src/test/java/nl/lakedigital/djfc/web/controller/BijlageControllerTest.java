package nl.lakedigital.djfc.web.controller;

import com.google.gson.Gson;
import nl.lakedigital.djfc.commons.json.JsonBijlage;
import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.BijlageService;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;

import static com.google.common.collect.Lists.newArrayList;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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

    @Test
    public void testOpslaanLijst() {
        JsonBijlage jsonBijlage = new JsonBijlage();
        jsonBijlage.setSoortEntiteit("RELATIE");
        final Bijlage bijlage = new Bijlage();
        bijlage.setSoortEntiteit(SoortEntiteit.RELATIE);
        final Long id = 9L;
        HttpServletRequest httpServletRequest = createMock(HttpServletRequest.class);
        expect(httpServletRequest.getHeader("ingelogdeGebruiker")).andReturn("46");
        expect(httpServletRequest.getHeader("trackAndTraceId")).andReturn("trackAndTraceId");

        expect(mapper.map(jsonBijlage, Bijlage.class)).andReturn(bijlage);
        bijlageService.opslaan(newArrayList(bijlage), SoortEntiteit.RELATIE, null);
        expectLastCall();

        replayAll();

        bijlageController.opslaan(newArrayList(jsonBijlage), httpServletRequest);

        verifyAll();
    }

    @Test
    public void testGenereerBestandsnaam() {
        assertThat(bijlageController.genereerBestandsnaam(), is(notNullValue()));
    }

    @Test
    public void testLees() {
        JsonBijlage jsonBijlage = new JsonBijlage();
        final Bijlage bijlage = new Bijlage();
        final Long id = 9L;

        expect(bijlageService.lees(id)).andReturn(bijlage);
        expect(mapper.map(bijlage, JsonBijlage.class)).andReturn(jsonBijlage);

        replayAll();

        assertThat(bijlageController.lees(id), is(jsonBijlage));

        verifyAll();
    }

    @Test
    public void testGetUploadPad() {
        String pad = "ditIsHetUploadPad";

        ReflectionTestUtils.setField(bijlageController, "uploadpad", pad);

        assertThat(bijlageController.getUploadPad(), is(new Gson().toJson(pad)));
    }
}