package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
public class BijlageRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BijlageRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();
    }

    public List<Bijlage> alleBijlagesBijEntiteit(SoortEntiteit soortEntiteit, Long entiteitId) {
        TypedQuery<Bijlage> query = entityManager.createNamedQuery("Bijlage.zoekBijlagesBijEntiteit", Bijlage.class);
        query.setParameter("soortBijlage", soortEntiteit);
        query.setParameter("entiteitId", entiteitId);

        return query.getResultList();
    }

    public Bijlage leesBijlage(Long id) {
        return entityManager.find(Bijlage.class, id);
    }

    public void opslaan(Bijlage bijlage) {
        entityManager.getTransaction().begin();
        if (bijlage.getId() == null) {
            entityManager.persist(bijlage);
        } else {
            entityManager.merge(bijlage);
        }
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void verwijder(Bijlage o) {
        entityManager.getTransaction().begin();

        entityManager.remove(o);

        entityManager.getTransaction().commit();
    }

    @Transactional
    public Bijlage lees(Long id) {
        return entityManager.find(Bijlage.class, id);
    }

    @Transactional
    public List<Bijlage> alles() {
        Query query = entityManager.createQuery("select e from Bijlage e");

        return query.getResultList();
    }

}