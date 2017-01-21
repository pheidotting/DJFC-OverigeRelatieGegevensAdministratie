package nl.lakedigital.djfc.service;

import nl.lakedigital.djfc.domain.TelefonieBestand;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.joda.time.LocalDateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.easymock.EasyMock.expect;

@Ignore
@RunWith(EasyMockRunner.class)
public class InlezenTelefonieBestandenServiceTest extends EasyMockSupport {
    @TestSubject
    private InlezenTelefonieBestandenService inlezenTelefonieBestandenService = new InlezenTelefonieBestandenService();

    @Mock
    private TelefonieBestandService telefonieBestandService;

    private String bestandsnaam1 = "out-0591377338-2912-20170102-185025-1483379425.187.wav";
    private String telefoonnummer1 = "0591377338";
    private LocalDateTime tijdstip1 = new LocalDateTime(2017, 1, 2, 18, 50, 25);
    private String bestandsnaam2 = "rg-8001-0614165929-20170102-115841-1483354721.74.wav";
    private String telefoonnummer2 = "0614165929";
    private LocalDateTime tijdstip2 = new LocalDateTime(2017, 1, 2, 11, 58, 41);
    private List<String> bestanden = newArrayList(bestandsnaam1, bestandsnaam2);

    @Test
    public void testRunMetAllemaalNietIngelezenBestanden() throws Exception {
        expect(telefonieBestandService.inlezenBestanden()).andReturn(bestanden);
        expect(telefonieBestandService.alleTelefonieBestanden()).andReturn(new ArrayList<TelefonieBestand>());

        TelefonieBestand telefonieBestand1 = new TelefonieBestand(bestandsnaam1, telefoonnummer1, tijdstip1);
        TelefonieBestand telefonieBestand2 = new TelefonieBestand(bestandsnaam2, telefoonnummer2, tijdstip2);

        telefonieBestandService.opslaan(newArrayList(telefonieBestand1, telefonieBestand2));

        replayAll();

        inlezenTelefonieBestandenService.run();

        verifyAll();
    }

    @Test
    public void testRunMetEenlNietIngelezenBestanden() throws Exception {
        TelefonieBestand telefonieBestand1 = new TelefonieBestand(bestandsnaam1, telefoonnummer1, tijdstip1);

        expect(telefonieBestandService.inlezenBestanden()).andReturn(bestanden);
        expect(telefonieBestandService.alleTelefonieBestanden()).andReturn(newArrayList(telefonieBestand1));

        TelefonieBestand telefonieBestand2 = new TelefonieBestand(bestandsnaam2, telefoonnummer2, tijdstip2);

        telefonieBestandService.opslaan(newArrayList(telefonieBestand2));

        replayAll();

        inlezenTelefonieBestandenService.run();

        verifyAll();
    }

    @Test
    public void testRunMetGeenlNietIngelezenBestanden() throws Exception {
        TelefonieBestand telefonieBestand1 = new TelefonieBestand(bestandsnaam1, telefoonnummer1, tijdstip1);
        TelefonieBestand telefonieBestand2 = new TelefonieBestand(bestandsnaam2, telefoonnummer2, tijdstip2);

        expect(telefonieBestandService.inlezenBestanden()).andReturn(bestanden);
        expect(telefonieBestandService.alleTelefonieBestanden()).andReturn(newArrayList(telefonieBestand1, telefonieBestand2));

        replayAll();

        inlezenTelefonieBestandenService.run();

        verifyAll();
    }
}