package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OpmerkingRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpmerkingRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();
    }

    public List<Opmerking> alleOpmerkingenBijEntiteit(SoortEntiteit soortEntiteit, Long entiteitId) {
        TypedQuery<Opmerking> query = entityManager.createNamedQuery("Opmerking.zoekOpmerkingenBijEntiteit", Opmerking.class);
        query.setParameter("soortEntiteit", soortEntiteit);
        query.setParameter("entiteitId", entiteitId);

        return query.getResultList();
    }

    public List<Opmerking> alleOpmerkingenVoorRelatie(Long relatie) {
        TypedQuery<Opmerking> query = entityManager.createNamedQuery("Opmerking.allesVoorRelatie", Opmerking.class);
        query.setParameter("relatie", relatie);

        return query.getResultList();
    }

    public void opslaan(Opmerking opmerking) {
        try {
            entityManager.getTransaction().begin();
        } catch (IllegalStateException ise) {
            LOGGER.debug("Fout opgetreden", ise);
        }
        if (opmerking.getId() == null) {
            entityManager.persist(opmerking);
        } else {
            entityManager.merge(opmerking);
        }
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void verwijder(Opmerking o) {
        entityManager.getTransaction().begin();

        entityManager.remove(o);

        entityManager.getTransaction().commit();
    }

    @Transactional
    public List<Opmerking> alles() {
        Query query = entityManager.createQuery("select e from Opmerking e");

        return query.getResultList();
    }

    @Transactional
    public Opmerking lees(Long id) {
        return entityManager.find(Opmerking.class, id);
    }

}