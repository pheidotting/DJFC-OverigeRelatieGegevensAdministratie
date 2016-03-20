package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.domain.RekeningNummer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RekeningNummerRepository extends AbstractRepository<RekeningNummer>{
    public RekeningNummerRepository() {
        super(RekeningNummer.class);
    }}
