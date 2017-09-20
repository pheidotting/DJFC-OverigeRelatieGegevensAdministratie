package nl.lakedigital.djfc.web.servlet;

import nl.lakedigital.djfc.reflection.ReflectionToStringBuilder;
import nl.lakedigital.djfc.repository.AdresRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckDatabaseConnectie implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckDatabaseConnectie.class);

    private AdresRepository adresRepository;

    public CheckDatabaseConnectie(AdresRepository adresRepository) {
        this.adresRepository = adresRepository;
    }

    @Override
    public void run() {
        LOGGER.info("check database connectie");
        adresRepository.getSession().getTransaction().begin();
        LOGGER.debug(ReflectionToStringBuilder.toString(adresRepository.getSession().createSQLQuery("/* ping */ SELECT 1").uniqueResult()));
        adresRepository.getSession().getTransaction().commit();
    }

}