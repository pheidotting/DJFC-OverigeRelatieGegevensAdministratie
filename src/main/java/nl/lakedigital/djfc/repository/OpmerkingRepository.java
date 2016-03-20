package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Opmerking;
import org.springframework.stereotype.Repository;

@Repository
public class OpmerkingRepository extends AbstractRepository<Opmerking> {
    public OpmerkingRepository() {
        super(Opmerking.class);
    }
}