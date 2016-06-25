package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonOpmerking;
import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.OpmerkingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/opmerking")
@Controller
public class OpmerkingController extends AbstractController<Opmerking, JsonOpmerking> {
    public OpmerkingController() {
        super(Opmerking.class, JsonOpmerking.class);
    }

    @Inject
    private OpmerkingService opmerkingService;

    @Override
    public AbstractService getService() {
        return opmerkingService;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/opslaan")
    @ResponseBody
    public void opslaan(@RequestBody List<JsonOpmerking> jsonEntiteiten, HttpServletRequest httpServletRequest) {
        zetSessieWaarden(httpServletRequest);

        for (JsonOpmerking jsonOpmerking : jsonEntiteiten) {
            opslaan(jsonOpmerking, httpServletRequest);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/opslaanOpmerking")
    @ResponseBody
    public Long opslaan(@RequestBody JsonOpmerking jsonOpmerking, HttpServletRequest httpServletRequest) {
        zetSessieWaarden(httpServletRequest);

        Opmerking opmerking = mapper.map(jsonOpmerking, Opmerking.class);

        opmerkingService.opslaan(opmerking);
        return opmerking.getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verwijder/{id}")
    @ResponseBody
    public void verwijder(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        zetSessieWaarden(httpServletRequest);

        opmerkingService.verwijder(id);
    }
}
