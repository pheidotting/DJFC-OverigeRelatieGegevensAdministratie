package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonRekeningNummer;
import nl.lakedigital.djfc.domain.RekeningNummer;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.RekeningNummerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

@RequestMapping("/rekeningnummer")
@Controller
public class RekeningNummerController extends AbstractController<RekeningNummer, JsonRekeningNummer> {
    public RekeningNummerController() {
        super(RekeningNummer.class, JsonRekeningNummer.class, RekeningNummerController.class);
    }

    @Inject
    private RekeningNummerService rekeningNummerService;

    @Override
    public AbstractService getService() {
        return rekeningNummerService;
    }

    @Override
    public void opslaan(List<JsonRekeningNummer> jsonEntiteiten) {
        goOpslaan(jsonEntiteiten);
    }
}
