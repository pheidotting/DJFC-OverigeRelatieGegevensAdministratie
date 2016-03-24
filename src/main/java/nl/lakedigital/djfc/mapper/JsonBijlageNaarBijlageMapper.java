package nl.lakedigital.djfc.mapper;

import nl.lakedigital.djfc.commons.json.JsonBijlage;
import nl.lakedigital.djfc.domain.Bijlage;
import nl.lakedigital.djfc.service.BijlageService;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

@Component
public class JsonBijlageNaarBijlageMapper extends AbstractMapper<JsonBijlage, Bijlage> implements JsonMapper {
    @Inject
    private BijlageService bijlageService;

    @Override
    public Bijlage map(JsonBijlage jsonBijlage, Object parent, Object bestaandObject) {
        Bijlage bijlage = new Bijlage();
        if (jsonBijlage.getId() != null) {
            bijlage = bijlageService.lees(jsonBijlage.getId());
        }

        bijlage.setOmschrijving(jsonBijlage.getOmschrijving());
        bijlage.setUploadMoment(LocalDateTime.now());
        bijlage.setBestandsNaam(jsonBijlage.getBestandsNaam());
        bijlage.setS3Identificatie(UUID.randomUUID().toString().replace("-", ""));

        return bijlage;
    }

    @Override
    public boolean isVoorMij(Object object) {
        return object instanceof JsonBijlage;
    }
}
