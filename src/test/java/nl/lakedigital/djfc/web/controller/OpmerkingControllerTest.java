package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonOpmerking;
import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.OpmerkingService;
import org.easymock.Mock;
import org.easymock.TestSubject;

public class OpmerkingControllerTest extends AbstractControllerTest<Opmerking, JsonOpmerking> {
    @TestSubject
    private OpmerkingController opmerkingController = new OpmerkingController();
    @Mock
    private OpmerkingService opmerkingService;

    @Override
    public AbstractController getController() {
        return opmerkingController;
    }

    @Override
    public AbstractService getService() {
        return opmerkingService;
    }

    @Override
    public Opmerking getEntiteit() {
        return new Opmerking();
    }

    @Override
    public JsonOpmerking getJsonEntiteit() {
        return new JsonOpmerking();
    }

    @Override
    public Class setType() {
        return Opmerking.class;
    }

    @Override
    public Class setJsonType() {
        return JsonOpmerking.class;
    }
}