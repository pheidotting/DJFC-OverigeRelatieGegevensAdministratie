//package nl.lakedigital.djfc.service;
//
//import nl.lakedigital.djfc.domain.predicates.SessieOpCookiePredicate;
//import nl.lakedigital.djfc.repository.AdresRepository;
//import nl.lakedigital.lakedigital.loginsystem.exception.NietGevondenException;
//import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import org.joda.time.LocalDate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.inject.Inject;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static com.google.common.collect.Iterables.filter;
//import static com.google.common.collect.Iterables.getFirst;
//
//@Service
//public class GebruikerService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(GebruikerService.class);
//
//    @Inject
//    private AdresRepository gebruikerRepository;
//
//    public List<ContactPersoon> alleContactPersonen(Long bedrijfsId) {
//        return gebruikerRepository.alleContactPersonen(bedrijfsId);
//    }
//
//
////    public void opslaanAdresBijRelatie(Adres adres, Long relatieId) {
////        Relatie relatie = (Relatie) gebruikerRepository.lees(relatieId);
////
////        adres.setRelatie(relatie);
////        relatie.getAdressen().add(adres);
////        gebruikerRepository.opslaan(relatie);
////    }
//
//    public void koppelenOnderlingeRelatie(Long relatieId, Long relatieMetId, String soortRelatie) {
//        LOGGER.info("koppelenOnderlingeRelatie({}, {}, {})", relatieId, relatieMetId, soortRelatie);
//
//        Relatie relatie = (Relatie) gebruikerRepository.lees(relatieId);
//        Relatie relatieMet = (Relatie) gebruikerRepository.lees(relatieMetId);
//
//        OnderlingeRelatieSoort onderlingeRelatieSoort = OnderlingeRelatieSoort.valueOf(soortRelatie);
//        OnderlingeRelatieSoort onderlingeRelatieSoortTegengesteld = OnderlingeRelatieSoort.getTegenGesteld(onderlingeRelatieSoort);
//
//        OnderlingeRelatie onderlingeRelatie = new OnderlingeRelatie(relatie, relatieMet, false, onderlingeRelatieSoort);
//        OnderlingeRelatie onderlingeRelatieTegengesteld = new OnderlingeRelatie(relatieMet, relatie, false, onderlingeRelatieSoortTegengesteld);
//
//        relatie.getOnderlingeRelaties().add(onderlingeRelatie);
//        relatieMet.getOnderlingeRelaties().add(onderlingeRelatieTegengesteld);
//
//        gebruikerRepository.opslaan(relatie);
//        gebruikerRepository.opslaan(relatieMet);
//
//    }
//
//
//    public Gebruiker lees(Long id) {
//        return gebruikerRepository.lees(id);
//    }
//
//    public Relatie leesRelatie(Long id) {
//        return (Relatie) this.lees(id);
//    }
//
//    public List<Relatie> alleRelaties(Long kantoor) {
//        return gebruikerRepository.alleRelaties(kantoor);
//    }
//
//    public void opslaan(Gebruiker gebruiker) {
//        System.out.println("Opslaan "+ ReflectionToStringBuilder.toString(gebruiker, ToStringStyle.SHORT_PREFIX_STYLE));
//        LOGGER.debug("Opslaan {}", ReflectionToStringBuilder.toString(gebruiker, ToStringStyle.SHORT_PREFIX_STYLE));
//
//        gebruikerRepository.opslaan(gebruiker);
//
//        // Als Gebruiker een Relatie is en BSN leeg is, moet er een taak worden aangemaakt
//        if (gebruiker instanceof Relatie) {
//            verstuurEvents((Relatie) gebruiker);
//        }
//    }
//
//    private void verstuurEvents(Relatie relatie) {
//        //        verstuurBsnEvent(relatie);
//        //        verstuurAdresEvent(relatie);
//        //        verstuurEmailEvent(relatie);
//    }
//
//    //    private void verstuurBsnEvent(Relatie relatie) {
//    //        if (isBlank(relatie.getBsn())) {
//    //            LOGGER.info("BSN is leeg, Taak aanmaken");
//    //
//    //            AanmakenTaak taak = new AanmakenTaak();
//    //            taak.setDatumTijdCreatie(LocalDateTime.now());
//    //            taak.setRelatie(relatie.getId());
//    //            taak.setSoort(SoortTaak.AANVULLEN_BSN);
//    //
//    //            aanmakenTaakSender.send(taak);
//    //        } else {
//    //            LOGGER.info("BSN gevuld, bericht versturen");
//    //
//    //            BsnAangevuld bsnAangevuld = new BsnAangevuld();
//    //            bsnAangevuld.setRelatie(relatie.getId());
//    //
//    //            bsnAangevuldSender.send(bsnAangevuld);
//    //        }
//    //    }
//    //
//    //    private void verstuurAdresEvent(Relatie relatie) {
//    //        if (relatie.getAdres() == null || !relatie.getAdres().isCompleet()) {
//    //            LOGGER.info("Adres is leeg of niet volledig, Taak aanmaken");
//    //
//    //            AanmakenTaak taak = new AanmakenTaak();
//    //            taak.setDatumTijdCreatie(LocalDateTime.now());
//    //            taak.setRelatie(relatie.getId());
//    //            taak.setSoort(SoortTaak.AANVULLEN_ADRES);
//    //
//    //            aanmakenTaakSender.send(taak);
//    //        } else if (relatie.getAdres() != null && relatie.getAdres().isCompleet()) {
//    //            LOGGER.info("Adres gevuld, bericht versturen");
//    //
//    //            AdresAangevuld adresAangevuld = new AdresAangevuld();
//    //            adresAangevuld.setRelatie(relatie.getId());
//    //
//    //            adresAangevuldSender.send(adresAangevuld);
//    //        }
//    //    }
//    //
//    //    private void verstuurEmailEvent(Relatie relatie) {
//    //        if (isBlank(relatie.getIdentificatie())) {
//    //            LOGGER.info("E-mailadres is leeg, Taak aanmaken");
//    //
//    //            AanmakenTaak taak = new AanmakenTaak();
//    //            taak.setDatumTijdCreatie(LocalDateTime.now());
//    //            taak.setRelatie(relatie.getId());
//    //            taak.setSoort(SoortTaak.AANVULLEN_EMAIL);
//    //
//    //            aanmakenTaakSender.send(taak);
//    //        } else {
//    //            LOGGER.info("E-mailadres gevuld, bericht versturen");
//    //
//    //            EmailadresAangevuld emailadresAangevuld = new EmailadresAangevuld();
//    //            emailadresAangevuld.setRelatie(relatie.getId());
//    //
//    //            emailAdresAangevuldSender.send(emailadresAangevuld);
//    //        }
//    //    }
//
//    public void verwijder(Long id) {
//        LOGGER.info("Verwijderen gebruiker met id " + id);
//
//        // Eerst ophalen
//        Gebruiker gebruiker = gebruikerRepository.lees(id);
//
//        LOGGER.debug("Opgehaalde gebruiker : ");
//        LOGGER.debug(ReflectionToStringBuilder.toString(gebruiker));
//
//        //        if (gebruiker instanceof Relatie) {
//        //            Relatie relatie = (Relatie) gebruiker;
//        //            for (Polis polis : relatie.getPolissen()) {
//        //                LOGGER.debug("Verwijder Polis :");
//        //                LOGGER.debug(ReflectionToStringBuilder.toString(polis));
//        //                polisRepository.verwijder(polis);
//        //                relatie.getPolissen().remove(polis);
//        //            }
//        //            for (Hypotheek hypotheek : relatie.getHypotheken()) {
//        //                LOGGER.debug("Verwijder Hypotheek :");
//        //                LOGGER.debug(ReflectionToStringBuilder.toString(hypotheek));
//        //                hypotheekRepository.verwijder(hypotheek);
//        //                relatie.getHypotheken().remove(hypotheek);
//        //            }
//        //        }
//        // en dan verwijderen
//        gebruikerRepository.verwijder(gebruiker);
//    }
//
//    public Gebruiker zoekOpSessieEnIpAdres(String sessie, String ipadres) throws NietGevondenException {
//        return gebruikerRepository.zoekOpSessieEnIpadres(sessie, ipadres);
//    }
//
//    public Sessie zoekSessieOp(String sessieId, String ipadres, Set<Sessie> sessies) {
//        for (Sessie sessie : sessies) {
//            if (sessie.getSessie().equals(sessieId) && sessie.getIpadres().equals(ipadres)) {
//                return sessie;
//            }
//        }
//
//        return null;
//    }
//
//    public Sessie zoekSessieOp(String cookieCode, Set<Sessie> sessies) {
//        return getFirst(filter(sessies, new SessieOpCookiePredicate(cookieCode)), null);
//    }
//
//    public Gebruiker zoek(String emailadres) throws NietGevondenException {
//        return gebruikerRepository.zoek(emailadres);
//    }
//
//    public void opslaan(Sessie sessie) {
//        gebruikerRepository.opslaan(sessie);
//    }
//
//    public void verwijder(Sessie sessie) {
//        gebruikerRepository.verwijder(sessie);
//    }
//
//    public void verwijderVerlopenSessies(Gebruiker gebr) {
//        Gebruiker gebruiker = gebruikerRepository.lees(gebr.getId());
//
//        List<Sessie> teVerwijderenSessies = new ArrayList<>();
//
//        for (Sessie sessie : gebruiker.getSessies()) {
//            if (sessie.getDatumLaatstGebruikt().isBefore(LocalDate.now().minusDays(3))) {
//                teVerwijderenSessies.add(sessie);
//            }
//        }
//
//        if (!teVerwijderenSessies.isEmpty()) {
//            for (Sessie sessie : teVerwijderenSessies) {
//                gebruiker.getSessies().remove(sessie);
//            }
//
//            gebruikerRepository.opslaan(gebruiker);
//        }
//    }
//
//    public List<Relatie> zoekOpNaamAdresOfPolisNummer(String zoekTerm) {
//        LOGGER.debug("zoekOpNaamAdresOfPolisNummer met zoekTerm " + zoekTerm);
//        Set<Relatie> relaties = new HashSet<Relatie>();
//        for (Gebruiker g : gebruikerRepository.zoekOpNaam(zoekTerm)) {
//            if (g instanceof Relatie) {
//                relaties.add((Relatie) g);
//            }
//        }
//        LOGGER.debug("Gevonden " + relaties.size() + " Relaties");
////        for (Gebruiker g : gebruikerRepository.zoekOpAdres(zoekTerm)) {
////            relaties.add((Relatie) g);
////        }
//        LOGGER.debug("Zoeken op telefoonnummer");
//        String zoekTermNumeriek = zoekTerm.replace(" ", "").replace("-", "");
//        try {
//            Long.valueOf(zoekTermNumeriek);
//        } catch (NumberFormatException nfe) {
//            zoekTermNumeriek = null;
//            LOGGER.trace("", nfe);
//        }
////        if (zoekTermNumeriek != null) {
////            for (Gebruiker g : gebruikerRepository.zoekRelatiesOpTelefoonnummer(zoekTermNumeriek)) {
////                relaties.add((Relatie) g);
////            }
////        }
//        LOGGER.debug("Zoeken op bedrijfnsaam");
////        for (Gebruiker g : gebruikerRepository.zoekRelatiesOpBedrijfsnaam(zoekTerm)) {
////            relaties.add((Relatie) g);
////        }
////
//        LOGGER.debug("Gevonden " + relaties.size() + " Relaties");
//        //        Polis polis = null;
//        //        try {
//        //            polis = polisRepository.zoekOpPolisNummer(zoekTerm, kantoorRepository.lees(1L));
//        //        } catch (NoResultException e) {
//        //            LOGGER.trace("Niks gevonden ", e);
//        //        }
//        //        if (polis != null) {
//        //            relaties.add(polis.getRelatie());
//        //        }
//        LOGGER.debug("Gevonden " + relaties.size() + " Relaties");
//
//        List<Relatie> ret = new ArrayList<>();
//        for (Relatie r : relaties) {
//            ret.add(r);
//        }
//
//        return ret;
//    }
//}
