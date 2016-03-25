package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.AdresService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@RequestMapping("/adres")
@Controller
public class AdresController extends AbstractController<Adres, JsonAdres> {
    public AdresController() {
        super(Adres.class, JsonAdres.class, AdresController.class);
    }

    @Inject
    private AdresService adresService;

    @Override
    public AbstractService getService() {
        return adresService;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/opslaan")
    @ResponseBody
    public void opslaan(@RequestBody List<JsonAdres> jsonEntiteiten) {
        goOpslaan(jsonEntiteiten);
    }

}
