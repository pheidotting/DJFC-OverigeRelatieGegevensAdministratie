package nl.lakedigital.djfc.repository;

import nl.lakedigital.djfc.domain.Telefoonnummer;
import org.springframework.stereotype.Repository;

@Repository
public class TelefoonnummerRepository extends AbstractRepository<Telefoonnummer> {
    public TelefoonnummerRepository() {
        super(Telefoonnummer.class);
    }
}
