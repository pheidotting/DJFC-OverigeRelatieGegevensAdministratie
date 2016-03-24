package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonBijlage;
import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.BijlageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@RequestMapping("/bijlage")
@Controller
public class BijlageController extends AbstractController<Bijlage, JsonBijlage> {
    public BijlageController() {
        super(Bijlage.class, JsonBijlage.class, BijlageController.class);
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
}
