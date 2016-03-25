package nl.lakedigital.it;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.commons.json.JsonOpmerking;
import nl.lakedigital.djfc.domain.SoortEntiteit;

import java.util.List;
import java.util.UUID;

public class OpmerkingTest extends AbstractTest<JsonOpmerking> {
    private OpmerkingClient opmerkingClient = new OpmerkingClient();

    public final List<String> fieldNames = Lists.newArrayList(//
            //            "tijd", //
            "medewerker", //
            "medewerkerId", //
            "opmerking", //
            "soortEntiteit", //
            "entiteitId" //
    );

    @Override
    public AbstractClient getClient() {
        return opmerkingClient;
    }

    @Override
    public List<String> getFields() {
        return fieldNames;
    }

    @Override
    public JsonOpmerking maakEntiteit(int teller, Long entiteitId, SoortEntiteit soortEntiteit) {
        JsonOpmerking jsonOpmerking = new JsonOpmerking();

        jsonOpmerking.setOpmerking(UUID.randomUUID().toString());
        jsonOpmerking.setTijd("2016-05-01 13:37");
        jsonOpmerking.setMedewerkerId(2L);
        jsonOpmerking.setEntiteitId(entiteitId);
        jsonOpmerking.setSoortEntiteit(soortEntiteit.name());

        return jsonOpmerking;
    }

    @Override
    public void wijzig(JsonOpmerking entiteit) {
        entiteit.setOpmerking("nieuweOpmerking");
    }
}
