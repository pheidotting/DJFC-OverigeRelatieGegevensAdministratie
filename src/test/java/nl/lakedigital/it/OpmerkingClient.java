package nl.lakedigital.it;

import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.commons.json.JsonOpmerking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpmerkingClient extends AbstractClient<JsonOpmerking> {
    private final String URL_LIJST = "http://localhost:7072/orga/rest/opmerking/alles";
    private final String URL_OPSLAAN = "http://localhost:7072/orga/rest/opmerking/opslaan";
    private final String URL_VERWIJDEREN = "http://localhost:7072/orga/rest/opmerking/verwijderen";

    @Override
    public List<JsonOpmerking> lijst(String soortEntiteit, Long entiteitId) {
        return uitvoerenGetLijst(URL_LIJST, JsonOpmerking.class, soortEntiteit, entiteitId.toString());
    }

    @Override
    public String opslaan(List<JsonOpmerking> jsonAdressen) {
        return aanroepenUrlPost(URL_OPSLAAN, jsonAdressen);
    }

    @Override
    public void verwijder(String soortEntiteit, Long entiteitId) {
        aanroepenUrlPostZonderBody(URL_VERWIJDEREN, soortEntiteit, entiteitId.toString());
    }
}
