package nl.lakedigital.it;

import nl.lakedigital.djfc.commons.json.JsonAdres;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
public class AdresClient extends AbstractClient<JsonAdres> {
    private final String URL_LIJST_ADRESSEN = "http://localhost:7072/orga/rest/adres/alles";
    private final String URL_OPSLAAN = "http://localhost:7072/orga/rest/adres/opslaan";
    private final String URL_VERWIJDEREN = "http://localhost:7072/orga/rest/adres/verwijderen";

    public List<JsonAdres> lijst(String soortEntiteit, Long entiteitId) {
        return uitvoerenGetLijst(URL_LIJST_ADRESSEN, JsonAdres.class, soortEntiteit, entiteitId.toString());
    }

    public String opslaan(List<JsonAdres> jsonAdressen) {
        return aanroepenUrlPost(URL_OPSLAAN, jsonAdressen);
    }


    public void verwijder(String soortEntiteit, Long entiteitId) {
         aanroepenUrlPostZonderBody (URL_VERWIJDEREN, soortEntiteit,entiteitId.toString());
    }
}
