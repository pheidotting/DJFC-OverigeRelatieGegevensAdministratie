package nl.lakedigital.it;

import com.google.common.collect.Lists;
import nl.lakedigital.djfc.client.AbstractClient;
import nl.lakedigital.djfc.commons.json.AbstracteJsonEntiteitMetSoortEnId;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static nl.lakedigital.assertion.Assert.assertEquals;

public abstract class AbstractTest<T extends AbstracteJsonEntiteitMetSoortEnId> {
    public abstract AbstractClient getClient();

    public abstract List<String> getFields();

    public abstract T maakEntiteit(int teller, Long entiteitId, SoortEntiteit soortEntiteit);

    public abstract void wijzig(T entiteit);

    @Test
    public void allesOpslaanTest() {
        List<Long> ids = Lists.newArrayList(3L, 6L, 9L);
        List<T> adressen = new ArrayList<>();

        int teller = 0;

        for (SoortEntiteit soortEntiteit : SoortEntiteit.values()) {
            for (Long id : ids) {
                T jsonAdres = maakEntiteit(++teller, id, soortEntiteit);

                adressen.add(jsonAdres);

                System.out.println(ReflectionToStringBuilder.toString(jsonAdres, ToStringStyle.SHORT_PREFIX_STYLE));
            }
        }

        getClient().opslaan(adressen);

        for (T jsonAdres : adressen) {

            List<T> adressedn = getClient().lijst(jsonAdres.getSoortEntiteit(), jsonAdres.getEntiteitId());

            T jsonAdres1 = adressedn.get(0);

            System.out.println(ReflectionToStringBuilder.toString(jsonAdres1, ToStringStyle.SHORT_PREFIX_STYLE));

            assertEquals(jsonAdres, jsonAdres1, getFields());

            getClient().verwijder(jsonAdres.getSoortEntiteit(), jsonAdres.getEntiteitId());
        }
    }


    @Test
    public void opslaanEnVerwijder() {
        SoortEntiteit soortEntiteit = SoortEntiteit.RELATIE;
        Long entiteitId = 5L;

        T entiteit1 = maakEntiteit(1, entiteitId, soortEntiteit);
        T entiteit2 = maakEntiteit(2, entiteitId, soortEntiteit);
        T entiteit3 = maakEntiteit(3, entiteitId, soortEntiteit);

        getClient().opslaan(Lists.newArrayList(entiteit1, entiteit2, entiteit3));

        List<T> adressenOpgehaald = getClient().lijst(soortEntiteit.name(), entiteitId);
        entiteit1 = adressenOpgehaald.get(0);
        entiteit2 = adressenOpgehaald.get(1);
        entiteit3 = adressenOpgehaald.get(2);

        wijzig(entiteit3);
        getClient().opslaan(Lists.newArrayList(entiteit1, entiteit2, entiteit3));

        adressenOpgehaald = getClient().lijst(soortEntiteit.name(), entiteitId);
        entiteit1 = adressenOpgehaald.get(0);
        entiteit2 = adressenOpgehaald.get(1);
        entiteit3 = adressenOpgehaald.get(2);
        assertEquals(3, adressenOpgehaald.size());

        getClient().opslaan(Lists.newArrayList(entiteit1, entiteit3));

        assertEquals(2, getClient().lijst(soortEntiteit.name(), entiteitId).size());
        getClient().verwijder(soortEntiteit.name(), entiteitId);

        assertEquals(0, getClient().lijst(soortEntiteit.name(), entiteitId).size());
    }
}
