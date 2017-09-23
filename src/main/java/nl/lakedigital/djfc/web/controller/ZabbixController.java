package nl.lakedigital.djfc.web.controller;

import nl.lakedigital.djfc.reflection.ReflectionToStringBuilder;
import nl.lakedigital.djfc.repository.AdresRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

@Controller
@RequestMapping("/zabbix")
public class ZabbixController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZabbixController.class);

    @Inject
    private AdresRepository adresRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/checkDatabase", produces = MediaType.TEXT_PLAIN)
    @ResponseBody
    public int checkDatabase() {
        try {
            adresRepository.getSession().getTransaction().begin();
            LOGGER.debug(ReflectionToStringBuilder.toString(adresRepository.getSession().createSQLQuery("/* ping */ SELECT 1").uniqueResult()));
            adresRepository.getSession().getTransaction().commit();
            return 1;
        } catch (Exception e) {
            LOGGER.error("Database niet beschikbaar", e);
            return 0;
        }
    }
}