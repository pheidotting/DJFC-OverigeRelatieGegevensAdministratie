package nl.lakedigital.it;

import com.google.gson.reflect.TypeToken;
import nl.lakedigital.djfc.commons.json.JsonOpmerking;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class OpmerkingClient extends AbstractClient<JsonOpmerking> {
    private final String URL_LIJST = "http://localhost:7072/orga/rest/opmerking/alles";
    private final String URL_OPSLAAN = "http://localhost:7072/orga/rest/opmerking/opslaan";
    private final String URL_VERWIJDEREN = "http://localhost:7072/orga/rest/opmerking/verwijderen";

    @Override
    protected Type getTypeToken() {
        return new TypeToken<ArrayList<JsonOpmerking>>() {
        }.getType();
    }

    @Override
    public List<JsonOpmerking> lijst(String soortEntiteit, Long entiteitId) {

        System.out.println("Aanroepen " + URL_LIJST);

        return uitvoerenGetLijst(URL_LIJST, JsonOpmerking.class, soortEntiteit, entiteitId.toString());
    }

    @Override
    public String opslaan(List<JsonOpmerking> jsonAdressen) {

        System.out.println("Aanroepen " + URL_OPSLAAN);
        for (JsonOpmerking jsonOpmerking : jsonAdressen) {
            System.out.println(ReflectionToStringBuilder.toString(jsonOpmerking, ToStringStyle.SHORT_PREFIX_STYLE));
        }
        System.out.println("# Aanroepen " + URL_OPSLAAN);

        return aanroepenUrlPost(URL_OPSLAAN, jsonAdressen);
    }

    @Override
    public void verwijder(String soortEntiteit, Long entiteitId) {

        System.out.println("Aanroepen " + URL_VERWIJDEREN);

        aanroepenUrlPostZonderBody(URL_VERWIJDEREN, soortEntiteit, entiteitId.toString());
    }
}