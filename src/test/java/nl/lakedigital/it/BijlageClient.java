package nl.lakedigital.it;

import com.google.gson.reflect.TypeToken;
import nl.lakedigital.djfc.commons.json.JsonBijlage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BijlageClient extends AbstractClient<JsonBijlage> {
    private final String URL_LIJST = "http://localhost:7072/orga/rest/bijlage/alles";
    private final String URL_OPSLAAN = "http://localhost:7072/orga/rest/bijlage/opslaan";
    private final String URL_VERWIJDEREN = "http://localhost:7072/orga/rest/bijlage/verwijderen";

    @Override
    protected Type getTypeToken() {
        return new TypeToken<ArrayList<JsonBijlage>>() {
        }.getType();
    }

    @Override
    public List<JsonBijlage> lijst(String soortEntiteit, Long entiteitId) {

        System.out.println("Aanroepen " + URL_LIJST);

        return uitvoerenGetLijst(URL_LIJST, JsonBijlage.class, soortEntiteit, entiteitId.toString());
    }

    @Override
    public String opslaan(List<JsonBijlage> jsonAdressen) {

        System.out.println("Aanroepen " + URL_OPSLAAN);

        return aanroepenUrlPost(URL_OPSLAAN, jsonAdressen);
    }

    @Override
    public void verwijder(String soortEntiteit, Long entiteitId) {

        System.out.println("Aanroepen " + URL_VERWIJDEREN);

        aanroepenUrlPostZonderBody(URL_VERWIJDEREN, soortEntiteit, entiteitId.toString());
    }

}
