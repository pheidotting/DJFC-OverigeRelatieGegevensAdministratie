package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.AdresService;
import org.easymock.Mock;
import org.easymock.TestSubject;

public class AdresControllerTest extends AbstractControllerTest<Adres, JsonAdres> {
    @TestSubject
    private AdresController adresController = new AdresController();
    @Mock
    private AdresService adresService;

    @Override
    public AbstractController getController() {
        return adresController;
    }

    @Override
    public AbstractService getService() {
        return adresService;
    }

    @Override
    public Adres getEntiteit() {
        return new Adres();
    }

    @Override
    public JsonAdres getJsonEntiteit() {
        return new JsonAdres();
    }

    @Override
    public Class setType() {
        return Adres.class;
    }

    @Override
    public Class setJsonType() {
        return JsonAdres.class;
    }
}