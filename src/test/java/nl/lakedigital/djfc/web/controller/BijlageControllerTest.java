package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonBijlage;
import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.BijlageService;
import org.easymock.Mock;
import org.easymock.TestSubject;

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
}