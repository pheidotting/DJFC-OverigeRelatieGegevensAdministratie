package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Adres;
import org.springframework.stereotype.Repository;

@Repository
public class AdresRepository extends AbstractRepository<Adres> {
    public AdresRepository() {
        super(Adres.class);
    }
}
