package nl.lakedigital.djfc.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Audited
@Entity
@Table(name = "TELEFOONNUMMER")
@NamedQueries({//
        @NamedQuery(name = "Telefoonnummer.zoekBijEntiteit", query = "select t from Telefoonnummer t where t.soortEntiteit = :soortEntiteit and t.entiteitId = :entiteitId")//
})
public class Telefoonnummer extends AbstracteEntiteitMetSoortEnId implements Serializable {
    private static final long serialVersionUID = -8991287195295458633L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(length = 10, name = "TELEFOONNUMMER")
    private String telefoonnummer;
    @Column(name = "TELEFOONNUMMERSOORT")
    @Enumerated(EnumType.STRING)
    private TelefoonnummerSoort soort;
    @Column(name = "OMSCHRIJVING", columnDefinition = "varchar(2500)")
    private String omschrijving;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public TelefoonnummerSoort getSoort() {
        return soort;
    }

    public void setSoort(TelefoonnummerSoort soort) {
        this.soort = soort;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Telefoonnummer)) {
            return false;
        }

        Telefoonnummer that = (Telefoonnummer) o;

        return new EqualsBuilder().append(getId(), that.getId()).append(getTelefoonnummer(), that.getTelefoonnummer()).append(getSoort(), that.getSoort()).append(getOmschrijving(), that.getOmschrijving()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).append(getTelefoonnummer()).append(getSoort()).append(getOmschrijving()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("telefoonnummer", telefoonnummer).append("soort", soort).append("omschrijving", omschrijving).toString();
    }
}