package nl.lakedigital.djfc.repository;

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
public class RekeningNummerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RekeningNummerRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();
    }

    @Transactional
    public void verwijder(RekeningNummer o) {
        entityManager.getTransaction().begin();

        entityManager.remove(o);

        entityManager.getTransaction().commit();
    }

    @Transactional
    public List<RekeningNummer> alles() {
        Query query = entityManager.createQuery("select e from RekeningNummer e");

        return query.getResultList();
    }

    @Transactional
    public void opslaan(RekeningNummer o) {
        entityManager.getTransaction().begin();

        if (o.getId() == null) {
            entityManager.persist(o);
        } else {
            entityManager.merge(o);
        }

        entityManager.getTransaction().commit();
    }

    @Transactional
    public RekeningNummer lees(Long id) {
        return entityManager.find(RekeningNummer.class, id);
    }
}
