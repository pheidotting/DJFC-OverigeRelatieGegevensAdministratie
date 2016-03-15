package nl.lakedigital.djfc.web.controller;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.mapper.Mapper;
import nl.lakedigital.djfc.service.AdresService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/adres")
@Controller
public class AdresController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdresController.class);

    @Inject
    private AdresService adresService;
    @Inject
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/alles/{soortentiteit}/{parentid}")
    @ResponseBody
    public List<JsonAdres> alles(@PathVariable("soortentiteit") String soortentiteit, @PathVariable("parentid") Long parentid) {
        LOGGER.debug("alles soortEntiteit {} parentId {}", soortentiteit, parentid);

        List<Adres> adresssen = adresService.alles(SoortEntiteit.valueOf(soortentiteit), parentid);
        List<JsonAdres> jsonAdressen = new ArrayList<>();

        for (Adres adres : adresssen) {
            jsonAdressen.add(mapper.map(adres, JsonAdres.class));
        }


        return jsonAdressen;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/opslaan")
    @ResponseBody
    public void opslaan(@RequestBody List<JsonAdres> jsonAdressen) {
        if (jsonAdressen != null && jsonAdressen.size() > 0) {
            JsonAdres eersteAdres = jsonAdressen.get(0);

            List<Adres> adressen = new ArrayList<>();
            for (JsonAdres jsonAdres : jsonAdressen) {
                adressen.add(mapper.map(jsonAdres, Adres.class));
            }
            adresService.opslaan(adressen, SoortEntiteit.valueOf(eersteAdres.getSoortEntiteit()), eersteAdres.getEntiteitId());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verwijderen/{soortentiteit}/{parentid}")
    @ResponseBody
    public void verwijderen(@PathVariable("soortentiteit") String soortentiteit, @PathVariable("parentid") Long parentid) {
        LOGGER.debug("Verwijderen adressen bij {} en {}", soortentiteit, parentid);

        adresService.verwijderen(SoortEntiteit.valueOf(soortentiteit), parentid);
    }
}
