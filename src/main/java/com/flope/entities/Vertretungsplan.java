/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peterkirchhoff
 */
@Entity
@Table(name = "vertretungsplan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vertretungsplan.findAll", query = "SELECT v FROM Vertretungsplan v"),
    @NamedQuery(name = "Vertretungsplan.findById", query = "SELECT v FROM Vertretungsplan v WHERE v.id = :id"),
    @NamedQuery(name = "Vertretungsplan.findByKuerzel", query = "SELECT v FROM Vertretungsplan v WHERE v.kuerzel = :kuerzel"),
    @NamedQuery(name = "Vertretungsplan.findByDatum", query = "SELECT v FROM Vertretungsplan v WHERE v.datum = :datum"),
    @NamedQuery(name = "Vertretungsplan.findByStunde", query = "SELECT v FROM Vertretungsplan v WHERE v.stunde = :stunde"),
    @NamedQuery(name = "Vertretungsplan.findByKlasse", query = "SELECT v FROM Vertretungsplan v WHERE v.klasse = :klasse"),
    @NamedQuery(name = "Vertretungsplan.findByAbwLehrer", query = "SELECT v FROM Vertretungsplan v WHERE v.abwLehrer = :abwLehrer"),
    @NamedQuery(name = "Vertretungsplan.findByVertrLehrer", query = "SELECT v FROM Vertretungsplan v WHERE v.vertrLehrer = :vertrLehrer"),
    @NamedQuery(name = "Vertretungsplan.findByRaum", query = "SELECT v FROM Vertretungsplan v WHERE v.raum = :raum"),
    @NamedQuery(name = "Vertretungsplan.findByKommentar", query = "SELECT v FROM Vertretungsplan v WHERE v.kommentar = :kommentar"),
    @NamedQuery(name = "Vertretungsplan.findByFach", query = "SELECT v FROM Vertretungsplan v WHERE v.fach = :fach")})
public class Vertretungsplan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "Kuerzel")
    private String kuerzel;
    @Size(max = 45)
    @Column(name = "Datum")
    private String datum;
    @Size(max = 45)
    @Column(name = "Stunde")
    private String stunde;
    @Size(max = 45)
    @Column(name = "Klasse")
    private String klasse;
    @Size(max = 45)
    @Column(name = "AbwLehrer")
    private String abwLehrer;
    @Size(max = 45)
    @Column(name = "VertrLehrer")
    private String vertrLehrer;
    @Size(max = 45)
    @Column(name = "Raum")
    private String raum;
    @Size(max = 45)
    @Column(name = "Kommentar")
    private String kommentar;
    @Size(max = 45)
    @Column(name = "Fach")
    private String fach;

    public Vertretungsplan() {
    }

    public Vertretungsplan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getStunde() {
        return stunde;
    }

    public void setStunde(String stunde) {
        this.stunde = stunde;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getAbwLehrer() {
        return abwLehrer;
    }

    public void setAbwLehrer(String abwLehrer) {
        this.abwLehrer = abwLehrer;
    }

    public String getVertrLehrer() {
        return vertrLehrer;
    }

    public void setVertrLehrer(String vertrLehrer) {
        this.vertrLehrer = vertrLehrer;
    }

    public String getRaum() {
        return raum;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vertretungsplan)) {
            return false;
        }
        Vertretungsplan other = (Vertretungsplan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.flope.entities.Vertretungsplan[ id=" + id + " ]";
    }
    
}
