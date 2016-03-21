package nl.lakedigital.djfc.mapper;

import nl.lakedigital.djfc.commons.json.JsonOpmerking;
import nl.lakedigital.djfc.domain.Opmerking;
import nl.lakedigital.djfc.service.OpmerkingService;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class JsonOpmerkingJsonOpmerkingMapper extends  AbstractMapper<JsonOpmerking,Opmerking>implements JsonMapper{
    @Inject
    private OpmerkingService opmerkingService;


    @Override
    public Opmerking map(JsonOpmerking jsonOpmerking, Object parent, Object bestaandOjbect) {
        Opmerking opmerking=new Opmerking();

        if(jsonOpmerking.getId()!=null){opmerking=opmerkingService.lees(jsonOpmerking.getId());}


        opmerking.setOpmerking(jsonOpmerking.getOpmerking());
        opmerking.setTijd(LocalDateTime.parse(jsonOpmerking.getTijd()));
        opmerking.setMedewerker(opmerking.getMedewerker());

        return opmerking;
    }

    @Override
    public boolean isVoorMij(Object object) {
        return object instanceof  JsonOpmerking;
    }
}
