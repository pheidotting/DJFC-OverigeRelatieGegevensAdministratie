package nl.lakedigital.djfc.service;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import nl.lakedigital.djfc.domain.AbstracteEntiteitMetSoortEnId;
import nl.lakedigital.djfc.domain.SoortEntiteit;
import nl.lakedigital.djfc.repository.AbstractRepository;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.collect.Iterables.filter;

public abstract class AbstractService<T extends AbstracteEntiteitMetSoortEnId> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    private final Class<T> type;

    public AbstractService(Class<T> type) {
        this.type = type;
    }

    public abstract AbstractRepository getRepository();

    public List<T> alles(SoortEntiteit soortEntiteit, Long parentId) {
        LOGGER.debug("alles soortEntiteit {} parentId {}", soortEntiteit, parentId);

        return getRepository().alles(soortEntiteit, parentId);
    }

    public T lees(Long id) {
        return (T)getRepository().lees(id);
    }

    public void opslaan(T adres) {
        getRepository().opslaan(Lists.newArrayList(adres));
    }

    public void opslaan(final List<T> entiteiten, SoortEntiteit soortEntiteit, Long entiteitId) {
        for(T t : entiteiten){
            System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.SHORT_PREFIX_STYLE));
        }

        List<T> lijstBestaandeNummer = getRepository().alles(soortEntiteit, entiteitId);

        //Verwijderen wat niet (meer) voorkomt
        Iterable<T> teVerwijderen = filter(lijstBestaandeNummer, new Predicate<T>() {
            @Override
            public boolean apply(T adres) {
                return !entiteiten.contains(adres);
            }
        });

        getRepository().verwijder(Lists.newArrayList(teVerwijderen));
        getRepository().opslaan(entiteiten);
    }

    public void verwijderen(SoortEntiteit soortEntiteit, Long entiteitId) {
        getRepository().verwijder(getRepository().alles(soortEntiteit, entiteitId));
    }

}
