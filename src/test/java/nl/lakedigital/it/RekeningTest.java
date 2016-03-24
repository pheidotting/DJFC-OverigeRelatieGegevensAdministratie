package nl.lakedigital.it;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.commons.json.JsonRekeningNummer;
import nl.lakedigital.djfc.domain.SoortEntiteit;

import java.util.List;
import java.util.UUID;

public class RekeningTest extends AbstractTest<JsonRekeningNummer> {
    private RekeningClient rekeningClient = new RekeningClient();

    @Override
    public AbstractClient getClient() {
        return rekeningClient;
    }

    public final List<String> fieldNames = Lists.newArrayList(//
            "bic", //
            "rekeningnummer");

    @Override
    public List<String> getFields() {
        return fieldNames;
    }

    @Override
    public JsonRekeningNummer maakEntiteit(int teller, Long entiteitId, SoortEntiteit soortEntiteit) {
        JsonRekeningNummer rekeningNummer = new JsonRekeningNummer();
        rekeningNummer.setEntiteitId(entiteitId);
        rekeningNummer.setSoortEntiteit(soortEntiteit.name());
        rekeningNummer.setBic("bic");
        rekeningNummer.setRekeningnummer(UUID.randomUUID().toString().replace("-", "").substring(0, 18));

        return rekeningNummer;
    }

    @Override
    public void wijzig(JsonRekeningNummer entiteit) {
        entiteit.setRekeningnummer("anderRekening");
    }
}
