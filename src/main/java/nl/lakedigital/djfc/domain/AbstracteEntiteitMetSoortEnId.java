package nl.lakedigital.djfc.domain;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Audited
public abstract class AbstracteEntiteitMetSoortEnId {
    @Enumerated(EnumType.STRING)
    @Column(length = 50, name = "SOORTENTITEIT")
    private SoortEntiteit soortEntiteit;
    @Column(name = "ENTITEITID")
    private Long entiteitId;

    public abstract Long getId();

    public SoortEntiteit getSoortEntiteit() {
        return soortEntiteit;
    }

    public void setSoortEntiteit(SoortEntiteit soortEntiteit) {
        this.soortEntiteit = soortEntiteit;
    }

    public Long getEntiteitId() {
        return entiteitId;
    }

    public void setEntiteitId(Long entiteitId) {
        this.entiteitId = entiteitId;
    }
}
