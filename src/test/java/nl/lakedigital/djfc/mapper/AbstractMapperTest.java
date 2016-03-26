package nl.lakedigital.djfc.mapper;

import nl.lakedigital.djfc.domain.SoortEntiteit;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

@RunWith(EasyMockRunner.class)
public abstract class AbstractMapperTest<T, U> extends EasyMockSupport {
    @TestSubject
    private Mapper mapper = new Mapper();

    public abstract AbstractMapper naarJsonMapper();

    public abstract AbstractMapper vanJsonMapper();

    public abstract Class setType();

    public abstract Class setJsonType();

    public abstract T maakEntiteit(SoortEntiteit soortEntiteit, Long entiteitId);

    public abstract U maakJsonEntiteit(SoortEntiteit soortEntiteit, Long entiteitId);

    private SoortEntiteit soortEntiteit = SoortEntiteit.HYPOTHEEK;
    private Long entiteitId = 5L;

    @Before
    public void init() {
        ReflectionTestUtils.setField(mapper, "mappers", newArrayList());
        ((List) ReflectionTestUtils.getField(mapper, "mappers")).add(naarJsonMapper());
        ((List) ReflectionTestUtils.getField(mapper, "mappers")).add(vanJsonMapper());
    }

    @Test
    public void testMapNaarJson() {
        assertEquals(maakJsonEntiteit(soortEntiteit, entiteitId), mapper.map(maakEntiteit(soortEntiteit, entiteitId), setJsonType()));

    }

    @Test
    public void testMapVanJson() {
        assertEquals(maakEntiteit(soortEntiteit, entiteitId), mapper.map(maakJsonEntiteit(soortEntiteit, entiteitId), setType()));
    }


}