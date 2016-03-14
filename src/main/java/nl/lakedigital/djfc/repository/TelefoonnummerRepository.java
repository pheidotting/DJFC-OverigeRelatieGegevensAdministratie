package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Telefoonnummer;
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
public class TelefoonnummerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelefoonnummerRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();
    }

    @Transactional
    public void verwijder(Telefoonnummer o) {
        entityManager.getTransaction().begin();

        entityManager.remove(o);

        entityManager.getTransaction().commit();
    }

    @Transactional
    public List<Telefoonnummer> alles() {
        Query query = entityManager.createQuery("select e from Telefoonnummer e");

        return query.getResultList();
    }

    @Transactional
    public void opslaan(Telefoonnummer o) {
        entityManager.getTransaction().begin();

        if (o.getId() == null) {
            entityManager.persist(o);
        } else {
            entityManager.merge(o);
        }

        entityManager.getTransaction().commit();
    }

    @Transactional
    public Telefoonnummer lees(Long id) {
        return entityManager.find(Telefoonnummer.class, id);
    }
}
