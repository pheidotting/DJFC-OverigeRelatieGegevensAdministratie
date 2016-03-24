package nl.lakedigital.it;

import com.google.gson.reflect.TypeToken;
import nl.lakedigital.djfc.commons.json.JsonAdres;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdresClient extends AbstractClient<JsonAdres> {
    private final String URL_LIJST = "http://localhost:7072/orga/rest/adres/alles";
    private final String URL_OPSLAAN = "http://localhost:7072/orga/rest/adres/opslaan";
    private final String URL_VERWIJDEREN = "http://localhost:7072/orga/rest/adres/verwijderen";

    @Override
    protected Type getTypeToken() {
        return new TypeToken<ArrayList<JsonAdres>>() {
        }.getType();
    }

    public List<JsonAdres> lijst(String soortEntiteit, Long entiteitId) {

        System.out.println("Aanroepen " + URL_LIJST);

        return uitvoerenGetLijst(URL_LIJST, JsonAdres.class, soortEntiteit, entiteitId.toString());
    }

    public String opslaan(List<JsonAdres> jsonAdressen) {

        System.out.println("Aanroepen " + URL_OPSLAAN);

        return aanroepenUrlPost(URL_OPSLAAN, jsonAdressen);
    }


    public void verwijder(String soortEntiteit, Long entiteitId) {

        System.out.println("Aanroepen " + URL_VERWIJDEREN);

        aanroepenUrlPostZonderBody(URL_VERWIJDEREN, soortEntiteit, entiteitId.toString());
    }
}
