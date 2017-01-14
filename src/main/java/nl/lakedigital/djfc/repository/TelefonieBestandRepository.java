package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.TelefonieBestand;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TelefonieBestandRepository {
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

    public List<TelefonieBestand> alles() {
        getTransaction().begin();

        List<TelefonieBestand> telefonieBestands = getSession().createQuery("select a from TelefonieBestand a").list();

        getTransaction().commit();

        return telefonieBestands;
    }

    public List<TelefonieBestand> allesMetTelefoonnummer(String telefoonnummer) {
        getTransaction().begin();

        Query query = getSession().getNamedQuery("TelefonieBestand.allesMetTelefoonnummer");
        query.setParameter("telefoonnummer", telefoonnummer);

        List<TelefonieBestand> telefonieBestands = query.list();

        getTransaction().commit();

        return telefonieBestands;
    }

    public void opslaan(List<TelefonieBestand> telefonieBestands) {
        getTransaction().begin();

        for (TelefonieBestand telefonieBestand : telefonieBestands) {
            if (telefonieBestand.getId() == null) {
                getSession().save(telefonieBestand);
            } else {
                getSession().merge(telefonieBestand);
            }
        }

        getTransaction().commit();
    }
}
