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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Florian
 */
@Entity
@Table(name = "display")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Display.findAll", query = "SELECT d FROM Display d"),
    @NamedQuery(name = "Display.findByDisplayID", query = "SELECT d FROM Display d WHERE d.displayID = :displayID"),
    @NamedQuery(name = "Display.findByName", query = "SELECT d FROM Display d WHERE d.name = :name"),
    @NamedQuery(name = "Display.findByStandort", query = "SELECT d FROM Display d WHERE d.standort = :standort"),
    @NamedQuery(name = "Display.findByCurrentURL", query = "SELECT d FROM Display d WHERE d.currentURL = :currentURL"),
    @NamedQuery(name = "Display.findByCurrentjob", query = "SELECT d FROM Display d WHERE d.currentjob = :currentjob")})
public class Display implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "currentjob")
    private int currentjob;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "currentURL")
    private String currentURL;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "displayID")
    private Integer displayID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "standort")
    private String standort;


    public Display() {
    }

    public Display(Integer displayID) {
        this.displayID = displayID;
    }

    public Display(Integer displayID, String name, String standort) {
        this.displayID = displayID;
        this.name = name;
        this.standort = standort;
    }

    public Integer getDisplayID() {
        return displayID;
    }

    public void setDisplayID(Integer displayID) {
        this.displayID = displayID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    public String getCurrentURL() {
        return currentURL;
    }

    public void setCurrentURL(String currentURL) {
        this.currentURL = currentURL;
    }

    public int getCurrentjob() {
        return currentjob;
    }

    public void setCurrentjob(int currentjob) {
        this.currentjob = currentjob;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (displayID != null ? displayID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Display)) {
            return false;
        }
        Display other = (Display) object;
        if ((this.displayID == null && other.displayID != null) || (this.displayID != null && !this.displayID.equals(other.displayID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.flope.entities.Display[ displayID=" + displayID + " ]";
    }

    
}


