package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.RekeningNummer;
import org.springframework.stereotype.Repository;

@Repository
public class RekeningNummerRepository extends AbstractRepository<RekeningNummer> {
    public RekeningNummerRepository() {
        super(RekeningNummer.class);
    }
}
