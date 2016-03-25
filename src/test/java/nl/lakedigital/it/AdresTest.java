package nl.lakedigital.it;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.SoortEntiteit;

import java.util.List;
import java.util.UUID;

public class AdresTest extends AbstractTest<JsonAdres> {
    private AdresClient adresClient = new AdresClient();

    public final List<String> jsonAdresFieldNames = Lists.newArrayList(//
            "straat", //
            "huisnummer", //
            "toevoeging", //
            "postcode", //
            "plaats", //
            "soortAdres", //
            "soortEntiteit", //
            "entiteitId" //
    );

    @Override
    public AbstractClient getClient() {
        return adresClient;
    }

    @Override
    public List<String> getFields() {
        return jsonAdresFieldNames;
    }

    @Override
    public JsonAdres maakEntiteit(int teller, Long entiteitId, SoortEntiteit soortEntiteit) {
        JsonAdres jsonAdres = new JsonAdres();

        jsonAdres.setToevoeging(UUID.randomUUID().toString().replace("-", ""));
        jsonAdres.setStraat(UUID.randomUUID().toString().replace("-", ""));
        jsonAdres.setPlaats(UUID.randomUUID().toString().replace("-", ""));
        jsonAdres.setSoortAdres("POSTADRES");
        jsonAdres.setPostcode(UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase());
        jsonAdres.setHuisnummer(Long.valueOf(teller));

        jsonAdres.setSoortEntiteit(soortEntiteit.name());
        jsonAdres.setEntiteitId(entiteitId);

        return jsonAdres;
    }

    @Override
    public void wijzig(JsonAdres entiteit) {
        entiteit.setStraat("nieuweStraat");

    }
}
