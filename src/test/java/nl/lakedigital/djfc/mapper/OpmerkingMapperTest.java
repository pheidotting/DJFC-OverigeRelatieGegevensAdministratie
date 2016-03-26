package nl.lakedigital.djfc.mapper;

import nl.lakedigital.djfc.commons.json.JsonOpmerking;
import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.domain.SoortEntiteit;

public class OpmerkingMapperTest extends AbstractMapperTest<Opmerking, JsonOpmerking> {
    private OpmerkingNaarJsonOpmerkingMapper naarJsonMapper = new OpmerkingNaarJsonOpmerkingMapper();
    private JsonOpmerkingJsonOpmerkingMapper vanJsonMapper = new JsonOpmerkingJsonOpmerkingMapper();

    @Override
    public AbstractMapper naarJsonMapper() {
        return naarJsonMapper;
    }

    @Override
    public AbstractMapper vanJsonMapper() {
        return vanJsonMapper;
    }

    @Override
    public Class setType() {
        return Opmerking.class;
    }

    @Override
    public Class setJsonType() {
        return JsonOpmerking.class;
    }

    @Override
    public Opmerking maakEntiteit(SoortEntiteit soortEntiteit, Long entiteitId) {
        Opmerking opmerking = new Opmerking();

        opmerking.setOpmerking("opmerking");
        opmerking.setMedewerker(1L);

        opmerking.setEntiteitId(entiteitId);
        opmerking.setSoortEntiteit(soortEntiteit);

        return opmerking;
    }

    @Override
    public JsonOpmerking maakJsonEntiteit(SoortEntiteit soortEntiteit, Long entiteitId) {
        JsonOpmerking opmerking = new JsonOpmerking();

        opmerking.setOpmerking("opmerking");
        opmerking.setMedewerkerId(1L);

        opmerking.setEntiteitId(entiteitId);
        opmerking.setSoortEntiteit(soortEntiteit.name());

        return opmerking;
    }
}
