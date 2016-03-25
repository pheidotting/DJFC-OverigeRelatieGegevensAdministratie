package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Bijlage;
import org.springframework.stereotype.Repository;

@Repository
public class BijlageRepository extends AbstractRepository<Bijlage> {
    public BijlageRepository() {
        super(Bijlage.class);
    }
}