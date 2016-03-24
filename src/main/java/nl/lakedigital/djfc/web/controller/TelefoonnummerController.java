package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonTelefoonnummer;
import nl.lakedigital.djfc.domain.Telefoonnummer;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.TelefoonnummerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@RequestMapping("/telefoonnummer")
@Controller
public class TelefoonnummerController extends AbstractController<Telefoonnummer, JsonTelefoonnummer> {
    public TelefoonnummerController() {
        super(Telefoonnummer.class, JsonTelefoonnummer.class, TelefoonnummerController.class);
    }

    @Inject
    private TelefoonnummerService telefoonnummerService;

    @Override
    public AbstractService getService() {
        return telefoonnummerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/opslaan")
    @ResponseBody
    @Override
    public void opslaan(@RequestBody List<JsonTelefoonnummer> jsonEntiteiten) {
        goOpslaan(jsonEntiteiten);
    }
}
