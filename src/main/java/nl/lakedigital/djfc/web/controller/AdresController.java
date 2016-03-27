package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.AdresService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET, value = "/lees/{id}")
    @ResponseBody
    public JsonAdres lees(@PathVariable Long id) {
        return mapper.map(adresService.lees(id), JsonAdres.class);
    }
}
