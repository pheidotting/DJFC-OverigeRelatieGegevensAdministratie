package nl.lakedigital.djfc.web.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.AdresService;
import nl.lakedigital.djfc.service.PostcodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RequestMapping("/adres")
@Controller
public class AdresController extends AbstractController<Adres, JsonAdres> {
    public AdresController() {
        super(Adres.class, JsonAdres.class);
    }

    @Inject
    private AdresService adresService;
    @Inject
    private PostcodeService postcodeService;

    @Override
    public AbstractService getService() {
        return adresService;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/opslaan")
    @ResponseBody
    public void opslaan(@RequestBody List<JsonAdres> jsonEntiteiten) {
        LOGGER.info("Opslaan lijst met {} entiteiten", jsonEntiteiten.size());

        goOpslaan(jsonEntiteiten);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/lees/{id}")
    @ResponseBody
    public JsonAdres lees(@PathVariable Long id) {
        return mapper.map(adresService.lees(id), JsonAdres.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ophalenAdresOpPostcode/{postcode}/{huisnummer}")
    @ResponseBody
    public JsonAdres ophalenAdresOpPostcode(@PathVariable("postcode") String postcode, @PathVariable("huisnummer") String huisnummer) {
        String adres = "https://postcode-api.apiwise.nl/v2/addresses/?postcode=" + postcode + "&number=" + huisnummer;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(adres);
        ClientResponse response = webResource.header("X-Api-Key", "FYEYGHHNFV3sZutux7LcX8ng8VizXWPk1HWxPPX9").accept("application/x-www-form-urlencoded; charset=UTF-8").get(ClientResponse.class);
        //        if (response.getStatus() != 200) {
        //            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        //        }

        String antwoord = response.getEntity(String.class);
        LOGGER.debug("Antwoord van de postcode api: {}", antwoord);

        JsonAdres jsonAdres = postcodeService.extraHeerAdres(antwoord);
        jsonAdres.setPostcode(postcode);
        if (huisnummer != null) {
            jsonAdres.setHuisnummer(Long.valueOf(huisnummer));
        }

        return jsonAdres;
    }
}
