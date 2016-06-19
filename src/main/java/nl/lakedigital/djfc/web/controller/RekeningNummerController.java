package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonRekeningNummer;
import nl.lakedigital.djfc.domain.RekeningNummer;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.RekeningNummerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/rekeningnummer")
@Controller
public class RekeningNummerController extends AbstractController<RekeningNummer, JsonRekeningNummer> {
    public RekeningNummerController() {
        super(RekeningNummer.class, JsonRekeningNummer.class);
    }

    @Inject
    private RekeningNummerService rekeningNummerService;

    @Override
    public AbstractService getService() {
        return rekeningNummerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/opslaan")
    @ResponseBody
    @Override
    public void opslaan(@RequestBody List<JsonRekeningNummer> jsonEntiteiten, HttpServletRequest httpServletRequest) {
        zetSessieWaarden(httpServletRequest);

        goOpslaan(jsonEntiteiten);
    }
}
