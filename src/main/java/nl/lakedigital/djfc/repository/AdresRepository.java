package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public List<Adres> alleAdressenBijLijstMetEntiteiten(List<Long> ids, SoortEntiteit soortEntiteit) {
        List<Adres> result = new ArrayList<>();

        for (Long id : ids) {
            result.addAll(alles(soortEntiteit, id));
        }

        return result;
    }
}
