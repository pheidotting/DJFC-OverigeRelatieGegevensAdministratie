package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.AbstracteEntiteitMetSoortEnId;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Transaction getTransaction() {
        Transaction transaction = getSession().getTransaction();
        if (transaction.getStatus() != TransactionStatus.ACTIVE) {
            transaction.begin();
        }

        return transaction;
    }

    public void verwijder(List<T> adressen) {
        if (getTransaction().getStatus() != TransactionStatus.ACTIVE) {
            getTransaction().begin();
        }

        for (T adres : adressen) {
            getSession().delete(adres);
        }

        getTransaction().commit();
    }

    public void opslaan(List<T> adressen) {
        if (getTransaction().getStatus() != TransactionStatus.ACTIVE) {
            getTransaction().begin();
        }

        for (T t : adressen) {
            if (t.getId() == null) {
                getSession().save(t);
            } else {
                getSession().merge(t);
            }
        }

        getTransaction().commit();
        getSession().close();
    }

    public T lees(Long id) {
        getTransaction().begin();

        T t = getSession().get(type, id);

        getTransaction().commit();

        return t;
    }

    public List<T> alles(SoortEntiteit soortEntiteit, Long entiteitId) {
        LOGGER.debug("moi");

        getTransaction().begin();

        Query query = getSession().getNamedQuery(getMyType() + ".zoekBijEntiteit");
        query.setParameter("soortEntiteit", soortEntiteit);
        query.setParameter("entiteitId", entiteitId);

        List<T> lijst = query.list();

        getTransaction().commit();

        return lijst;
    }

    public void verwijder(T t){
        getTransaction().begin();

        getSession().delete(t);

        getTransaction().commit();
    }
}
