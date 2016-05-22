package nl.lakedigital.it;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.client.oga.AbstractOgaClient;
import nl.lakedigital.djfc.client.oga.BijlageClient;
import nl.lakedigital.djfc.commons.json.JsonBijlage;
import nl.lakedigital.djfc.domain.SoortEntiteit;

import java.util.List;
import java.util.UUID;


public class BijlageTest extends AbstractTest<JsonBijlage> {
    private BijlageClient bijlageClient = new BijlageClient(7072);

    @Override
    public AbstractOgaClient getClient() {
        return bijlageClient;
    }

    public final List<String> fieldNames = Lists.newArrayList(//
            //            "bestandsNaam"//, //
            //            "sooomschrijvingrt"
    );

    @Override
    public List<String> getFields() {
        return fieldNames;
    }

    @Override
    public JsonBijlage maakEntiteit(int teller, Long entiteitId, SoortEntiteit soortEntiteit) {
        JsonBijlage jsonBijlage = new JsonBijlage();
        jsonBijlage.setBestandsNaam(UUID.randomUUID().toString());
        jsonBijlage.setOmschrijving(UUID.randomUUID().toString());
        jsonBijlage.setEntiteitId(entiteitId);
        jsonBijlage.setSoortEntiteit(soortEntiteit.name());

        return jsonBijlage;
    }

    @Override
    public JsonBijlage maakEntiteitVoorZoeken(String zoekWaarde, SoortEntiteit soortEntiteit, Long entiteitId) {
        JsonBijlage jsonBijlage = new JsonBijlage();
        jsonBijlage.setOmschrijving(zoekWaarde);
        jsonBijlage.setEntiteitId(entiteitId);
        jsonBijlage.setSoortEntiteit(soortEntiteit.name());

        return jsonBijlage;
    }

    @Override
    public void wijzig(JsonBijlage entiteit) {
        entiteit.setOmschrijving(UUID.randomUUID().toString());
    }
}
