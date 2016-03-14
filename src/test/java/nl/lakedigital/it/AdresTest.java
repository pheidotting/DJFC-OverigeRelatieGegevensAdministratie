package nl.lakedigital.it;

import com.google.common.collect.Lists;
//import nl.lakedigital.djfc.client.AdresClient;
import nl.lakedigital.djfc.commons.json.JsonAdres;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AdresTest {
//    @Inject
    private AdresClient adresClient = new AdresClient();

    //    public final List<String> jsonRelatieFieldNames = Lists.newArrayList(//
    //            "identificatie", //
    //            "roepnaam", //
    //            "voornaam", //
    //            "tussenvoegsel", //

    //            "achternaam",//
    //            "bsn", //
    //            "geboorteDatum", //
    //            "overlijdensdatum", //
    //            "geslacht", //
    //            "burgerlijkeStaat"//
    //    );



    @Test
    public void allesOpslaanTest() {
        List<Long> ids = Lists.newArrayList(3L, 6L, 9L);
        List<JsonAdres> adressen = new ArrayList<>();

        for (SoortEntiteit soortEntiteit : SoortEntiteit.values()) {
            for (Long id : ids) {
                JsonAdres jsonAdres = new JsonAdres();

                jsonAdres.setEntiteitId(id);
                jsonAdres.setSoortEntiteit(soortEntiteit.name());

                adressen.add(jsonAdres);

                System.out.println(ReflectionToStringBuilder.toString(jsonAdres,ToStringStyle.SHORT_PREFIX_STYLE));
            }
        }

        adresClient.opslaan(adressen);
    }

    @Test
    public void test() {
//        List<JsonAdres> adresssen = adresClient.lijstAdressen(SoortEntiteit.POLIS.name(), 3L);

//        for (JsonAdres jsonAdres : adresssen) {
//            System.out.println(ReflectionToStringBuilder.toString(jsonAdres, ToStringStyle.SHORT_PREFIX_STYLE));
//        }
    }
    //    public void opslaanRelatieMinimaal() {
    //        Long medewerkerId  = 3L;
    //        JsonRelatie relatie = new JsonRelatie();
    //        relatie.setVoornaam("Tony");
    //        relatie.setAchternaam("Stark");
    //        relatie.setKantoor(1L);
    //
    //        String sId = relatieClient.opslaanRelatie(relatie);
    //        org.junit.Assert.assertEquals(1,relatieClient.lijstRelaties(medewerkerId).getJsonRelaties().size());
    //        Long id = Long.valueOf(sId);
    //
    //        JsonRelatie opgeslagen = relatieClient.leesRelatie(id);
    //        relatie.setId(id);
    //
    //        assertEquals(relatie, opgeslagen, jsonRelatieFieldNames);
    //
    //        relatie.setBsn("bsn");
    //        relatieClient.opslaanRelatie(relatie);
    //        org.junit.Assert.assertEquals(1,relatieClient.lijstRelaties(medewerkerId).getJsonRelaties().size());
    //
    //        opgeslagen = relatieClient.leesRelatie(id);
    //
    //        assertEquals(relatie, opgeslagen, jsonRelatieFieldNames);
    //
    //        relatie.setTussenvoegsel("tt");relatie.setBurgerlijkeStaat("Gehuwd GVG");relatie.setGeboorteDatum("1979-09-06");
    //        relatie.setGeslacht("Man");relatie.setIdentificatie("id");relatie.setOverlijdensdatum("2015-03-01");relatie.setRoepnaam("Iron Man");
    //
    //        relatieClient.opslaanRelatie(relatie);
    //        org.junit.Assert.assertEquals(1,relatieClient.lijstRelaties(medewerkerId).getJsonRelaties().size());
    //
    //        opgeslagen = relatieClient.leesRelatie(id);
    //
    //        assertEquals(relatie, opgeslagen, jsonRelatieFieldNames);
    //    }


}
