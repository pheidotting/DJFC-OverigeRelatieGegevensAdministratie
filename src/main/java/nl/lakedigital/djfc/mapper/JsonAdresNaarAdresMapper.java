package nl.lakedigital.djfc.mapper;

import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.Adres;
import nl.lakedigital.djfc.service.AdresService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class JsonAdresNaarAdresMapper extends AbstractMapper<JsonAdres, Adres> implements JsonMapper {
    @Inject
    private AdresService adresService;

    @Override
    public Adres map(JsonAdres jsonAdres, Object parent, Object bestaandObject) {
        Adres adres = new Adres();

        if(jsonAdres.getId()!=null){
            adres = adresService.lees(jsonAdres.getId());
        }

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

        return adres;
    }

    @Override
    public boolean isVoorMij(Object object) {
        return object instanceof JsonAdres;
    }
}
