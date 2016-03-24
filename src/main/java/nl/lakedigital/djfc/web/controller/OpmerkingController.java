package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.commons.json.JsonOpmerking;
import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.OpmerkingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@RequestMapping("/opmerking")
@Controller
public class OpmerkingController extends AbstractController<Opmerking, JsonOpmerking> {
    public OpmerkingController() {
        super(Opmerking.class, JsonOpmerking.class, OpmerkingController.class);
    }

    @Inject
    private OpmerkingService opmerkingService;

    @Override
    public AbstractService getService() {
        return opmerkingService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/opslaan")
    @ResponseBody
    public void opslaan(@RequestBody List<JsonOpmerking> jsonEntiteiten) {
        goOpslaan(jsonEntiteiten);
    }
}
