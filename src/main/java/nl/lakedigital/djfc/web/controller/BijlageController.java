package nl.lakedigital.djfc.web.controller;

import com.google.gson.Gson;
import nl.lakedigital.djfc.commons.json.JsonBijlage;
import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.service.AbstractService;
import nl.lakedigital.djfc.service.BijlageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Configuration
@RequestMapping("/bijlage")
@Controller
@PropertySources({@PropertySource("classpath:application.properties"), @PropertySource(value = "file:app.properties", ignoreResourceNotFound = true)})
public class BijlageController extends AbstractController<Bijlage, JsonBijlage> {
    public BijlageController() {
        super(Bijlage.class, JsonBijlage.class);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Inject
    private BijlageService bijlageService;
    @Value("${uploadpad}")
    private String uploadpad;

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

    @RequestMapping(method = RequestMethod.GET, value = "/genereerBestandsnaam")
    @ResponseBody
    public String genereerBestandsnaam() {
        String identificatie = UUID.randomUUID().toString().replace("-", "");

        return new Gson().toJson(identificatie);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUploadPad")
    @ResponseBody
    public String getUploadPad() {
        return new Gson().toJson(uploadpad);
    }
}
