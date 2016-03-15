package nl.lakedigital.djfc.mapper;

import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import org.springframework.stereotype.Component;

@Component
public class JsonAdresNaarAdresMapper extends AbstractMapper<JsonAdres, Adres> implements JsonMapper {

    @Override
    public Adres map(JsonAdres jsonAdres, Object parent, Object bestaandObject) {
        Adres adres = new Adres();

        adres.setHuisnummer(jsonAdres.getHuisnummer());
        adres.setId(jsonAdres.getId());
        adres.setHuisnummer(jsonAdres.getHuisnummer());
        adres.setPlaats(jsonAdres.getPlaats());
        adres.setPostcode(jsonAdres.getPostcode());
        adres.setStraat(jsonAdres.getStraat());
        adres.setToevoeging(jsonAdres.getToevoeging());
        if (jsonAdres.getSoortAdres() != null) {
            adres.setSoortAdres(Adres.SoortAdres.valueOf(jsonAdres.getSoortAdres()));
        }

        adres.setSoortEntiteit(SoortEntiteit.valueOf(jsonAdres.getSoortEntiteit()));
        adres.setEntiteitId(jsonAdres.getEntiteitId());

        return adres;
    }

    @Override
    public boolean isVoorMij(Object object) {
        return object instanceof JsonAdres;
    }
}
