package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.AbstracteEntiteitMetSoortEnId;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class AbstractRepository<T extends AbstracteEntiteitMetSoortEnId> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);

    private final Class<T> type;

    public AbstractRepository(Class<T> type) {
        this.type = type;
    }

    private String getMyType() {
        return this.type.getSimpleName();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();
    }

    @Transactional
    public void verwijder(List<T> adressen) {
        entityManager.getTransaction().begin();

        for (T adres : adressen) {
            entityManager.remove(adres);
        }

        entityManager.getTransaction().commit();
    }

    @Transactional
    public void opslaan(List<T> adressen) {

        entityManager.getTransaction().begin();

        for (T adres : adressen) {
            System.out.println(ReflectionToStringBuilder.toString(adres, ToStringStyle.SHORT_PREFIX_STYLE));

            if (adres.getId() == null) {
                entityManager.persist(adres);
            } else {
                entityManager.merge(adres);
            }
        }

        entityManager.getTransaction().commit();
    }

    public T lees(Long id) {
        return entityManager.find(type, id);
    }

    public List<T> alles(SoortEntiteit soortEntiteit, Long entiteitId) {
        TypedQuery<T> query = entityManager.createNamedQuery(getMyType()+".zoekBijEntiteit", type);
        query.setParameter("soortEntiteit", soortEntiteit);
        query.setParameter("entiteitId", entiteitId);

        return query.getResultList();
    }
}
