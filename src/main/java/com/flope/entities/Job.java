/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author peterkirchhoff
 */
@Entity
@Table(name = "job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
    @NamedQuery(name = "Job.findByJobID", query = "SELECT j FROM Job j WHERE j.jobID = :jobID"),
    @NamedQuery(name = "Job.findByJobname", query = "SELECT j FROM Job j WHERE j.jobname = :jobname"),
    @NamedQuery(name = "Job.findByCreatedby", query = "SELECT j FROM Job j WHERE j.createdby = :createdby"),
    @NamedQuery(name = "Job.findByTimeStart", query = "SELECT j FROM Job j WHERE j.timeStart = :timeStart"),
    @NamedQuery(name = "Job.findByTimeEnd", query = "SELECT j FROM Job j WHERE j.timeEnd = :timeEnd"),
    @NamedQuery(name = "Job.findByFileID", query = "SELECT j FROM Job j WHERE j.fileID = :fileID"),
    @NamedQuery(name = "Job.findByMessageID", query = "SELECT j FROM Job j WHERE j.messageID = :messageID"),
    @NamedQuery(name = "Job.findByAnzeigeID", query = "SELECT j FROM Job j WHERE j.anzeigeID = :anzeigeID"),
    @NamedQuery(name = "Job.findByRecurring", query = "SELECT j FROM Job j WHERE j.recurring = :recurring"),
    @NamedQuery(name = "Job.findByJobtype", query = "SELECT j FROM Job j WHERE j.jobtype = :jobtype"),
    @NamedQuery(name = "Job.findByConnectedfile", query = "SELECT j FROM Job j WHERE j.connectedfile = :connectedfile"),
    @NamedQuery(name = "Job.findByOptlock", query = "SELECT j FROM Job j WHERE j.optlock = :optlock"),
    @NamedQuery(name = "Job.findByFinished", query = "SELECT j FROM Job j WHERE j.finished = :finished")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "jobID")
    private Integer jobID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "jobname")
    private String jobname;
    @Size(max = 45)
    @Column(name = "createdby")
    private String createdby;
    @Column(name = "timeStart")
    private long timeStart;
    @Column(name = "timeEnd")
    private long timeEnd;
    @Size(max = 256)
    @Column(name = "fileID")
    private String fileID;
    @Size(max = 256)
    @Column(name = "messageID")
    private String messageID;
    @Size(max = 256)
    @Column(name = "AnzeigeID")
    private String anzeigeID;
    @Column(name = "recurring")
    private Boolean recurring;
    @Size(max = 45)
    @Column(name = "jobtype")
    private String jobtype;
    @Size(max = 256)
    @Column(name = "connectedfile")
    private String connectedfile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "optlock")
    private long optlock;
    @Column(name = "finished")
    private Boolean finished;

    public Job() {
    }

    public Job(Integer jobID) {
        this.jobID = jobID;
    }

    public Job(Integer jobID, String jobname, long optlock) {
        this.jobID = jobID;
        this.jobname = jobname;
        this.optlock = optlock;
    }

    public Integer getJobID() {
        return jobID;
    }

    public void setJobID(Integer jobID) {
        this.jobID = jobID;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    public long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getAnzeigeID() {
        return anzeigeID;
    }

    public void setAnzeigeID(String anzeigeID) {
        this.anzeigeID = anzeigeID;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getConnectedfile() {
        return connectedfile;
    }

    public void setConnectedfile(String connectedfile) {
        this.connectedfile = connectedfile;
    }

    public long getOptlock() {
        return optlock;
    }

    public void setOptlock(long optlock) {
        this.optlock = optlock;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobID != null ? jobID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.jobID == null && other.jobID != null) || (this.jobID != null && !this.jobID.equals(other.jobID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.flope.entities.Job[ jobID=" + jobID + " ]";
    }
    
}
