package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonBijlage;
import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.BijlageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RequestMapping("/bijlage")
@Controller
public class BijlageController extends AbstractController<Bijlage, JsonBijlage> {
    public BijlageController() {
        super(Bijlage.class, JsonBijlage.class);
    }

    @Inject
    private BijlageService bijlageService;

    @Override
    public AbstractService getService() {
        return bijlageService;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/opslaan")
    @ResponseBody
    public void opslaan(@RequestBody List<JsonBijlage> jsonEntiteiten) {
        goOpslaan(jsonEntiteiten);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/opslaanBijlage")
    @ResponseBody
    public Long opslaan(JsonBijlage jsonBijlage) {
        Bijlage bijlage = mapper.map(jsonBijlage, Bijlage.class);
        bijlageService.opslaan(bijlage);

        return bijlage.getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verwijder/{id}")
    @ResponseBody
    public void verwijder(@PathVariable("id") Long id) {
        bijlageService.verwijder(id);
    }
}
