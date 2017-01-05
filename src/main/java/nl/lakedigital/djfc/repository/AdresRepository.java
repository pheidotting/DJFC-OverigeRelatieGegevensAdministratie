package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Adres;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdresRepository extends AbstractRepository<Adres> {
    public AdresRepository() {
        super(Adres.class);
    }

    public List<Adres> alles() {
        getSession().getTransaction().begin();

        List<Adres> adressen = getSession().createQuery("select a from Adres a").list();

        getSession().getTransaction().commit();

        return adressen;
    }
}
