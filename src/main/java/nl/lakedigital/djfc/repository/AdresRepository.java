package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Adres;
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
public class AdresRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdresRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();
    }

    @Transactional
    public void verwijder(Adres o) {
        entityManager.getTransaction().begin();

        entityManager.remove(o);

        entityManager.getTransaction().commit();
    }

    @Transactional
    public void opslaan(Adres o) {
        System.out.println(ReflectionToStringBuilder.toString(o, ToStringStyle.SHORT_PREFIX_STYLE));

        entityManager.getTransaction().begin();

        if (o.getId() == null) {
            entityManager.persist(o);
        } else {
            entityManager.merge(o);
        }

        entityManager.getTransaction().commit();
    }

    @Transactional
    public Adres lees(Long id) {
        return entityManager.find(Adres.class, id);
    }

    public List<Adres> alles(SoortEntiteit soortEntiteit, Long entiteitId) {
        TypedQuery<Adres> query = entityManager.createNamedQuery("Adres.zoekAdressgenBijEntiteit", Adres.class);
        query.setParameter("soortEntiteit", soortEntiteit);
        query.setParameter("entiteitId", entiteitId);

        return query.getResultList();
    }
}
