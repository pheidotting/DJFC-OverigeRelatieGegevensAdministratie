package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.mapper.Mapper;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.AdresService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/adres")
@Controller
public class AdresController extends AbstractController<Adres,JsonAdres>{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdresController.class);

    public AdresController() {
        super(Adres.class, JsonAdres.class);
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
