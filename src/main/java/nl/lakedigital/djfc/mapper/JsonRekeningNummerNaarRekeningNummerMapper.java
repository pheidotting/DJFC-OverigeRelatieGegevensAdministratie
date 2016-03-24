package nl.lakedigital.djfc.mapper;

import nl.lakedigital.djfc.commons.json.JsonRekeningNummer;
import nl.lakedigital.djfc.domain.RekeningNummer;
import nl.lakedigital.djfc.service.RekeningNummerService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class JsonRekeningNummerNaarRekeningNummerMapper extends AbstractMapper<JsonRekeningNummer, RekeningNummer> implements JsonMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonRekeningNummerNaarRekeningNummerMapper.class);

    @Inject
    private RekeningNummerService rekeningNummerService;

    @Override
    public RekeningNummer map(JsonRekeningNummer jsonRekeningNummer, Object parent, Object bestaandOjbect) {
        LOGGER.debug(ReflectionToStringBuilder.toString(jsonRekeningNummer, ToStringStyle.SHORT_PREFIX_STYLE));
        LOGGER.debug(ReflectionToStringBuilder.toString(parent, ToStringStyle.SHORT_PREFIX_STYLE));
        LOGGER.debug(ReflectionToStringBuilder.toString(bestaandOjbect, ToStringStyle.SHORT_PREFIX_STYLE));

        RekeningNummer rekeningNummer = new RekeningNummer();
        if (jsonRekeningNummer.getId() != null) {
            rekeningNummer = rekeningNummerService.lees(jsonRekeningNummer.getId());
        }
        rekeningNummer.setRekeningnummer(jsonRekeningNummer.getRekeningnummer());
        rekeningNummer.setBic(jsonRekeningNummer.getBic());

        return rekeningNummer;
    }

    @Override
    public boolean isVoorMij(Object object) {
        return object instanceof JsonRekeningNummer;
    }
}
