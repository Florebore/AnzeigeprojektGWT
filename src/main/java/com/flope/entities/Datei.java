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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Florian
 */
@Entity
@Table(name = "datei")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datei.findAll", query = "SELECT d FROM Datei d"),
    @NamedQuery(name = "Datei.findByFileID", query = "SELECT d FROM Datei d WHERE d.fileID = :fileID"),
    @NamedQuery(name = "Datei.findByFilename", query = "SELECT d FROM Datei d WHERE d.filename = :filename"),
    @NamedQuery(name = "Datei.findByFiletype", query = "SELECT d FROM Datei d WHERE d.filetype = :filetype"),
    @NamedQuery(name = "Datei.findByUploadedby", query = "SELECT d FROM Datei d WHERE d.uploadedby = :uploadedby"),
    @NamedQuery(name = "Datei.findByLocation", query = "SELECT d FROM Datei d WHERE d.location = :location")})
public class Datei implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fileID", unique = true, nullable = false)
    private Integer fileID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "filename")
    private String filename;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "filetype")
    private String filetype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "uploadedby")
    private String uploadedby;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "location")
    private String location;
    @Lob
    @Column(name = "filecontent")
    private byte[] filecontent;
    //Version checking for concurrent db access
    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version;

    public Datei() {
    }

    public Datei(Integer fileID) {
        this.fileID = fileID;
    }

    public Datei(Integer fileID, String filename, String filetype, String uploadedby, String location) {
        this.fileID = fileID;
        this.filename = filename;
        this.filetype = filetype;
        this.uploadedby = uploadedby;
        this.location = location;
    }

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getUploadedby() {
        return uploadedby;
    }

    public void setUploadedby(String uploadedby) {
        this.uploadedby = uploadedby;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getFilecontent() {
        return filecontent;
    }

    public void setFilecontent(byte[] filecontent) {
        this.filecontent = filecontent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileID != null ? fileID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datei)) {
            return false;
        }
        Datei other = (Datei) object;
        if ((this.fileID == null && other.fileID != null) || (this.fileID != null && !this.fileID.equals(other.fileID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.flope.entities.Datei[ fileID=" + fileID + " ]";
    }
    
}
