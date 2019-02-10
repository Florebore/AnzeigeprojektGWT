/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.entities;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Florian
 */
@Entity
@Table(name = "userdata"/*, catalog = "database_anzeige", schema = "database_anzeige"*/)
//Optionen aus Table Klammer entfernt, catalog = "database_anzeige", schema = ""
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Userdata.findAll", query = "SELECT u FROM Userdata u")
    , @NamedQuery(name = "Userdata.findById", query = "SELECT u FROM Userdata u WHERE u.id = :id")
    , @NamedQuery(name = "Userdata.findByFirstname", query = "SELECT u FROM Userdata u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "Userdata.findByLastname", query = "SELECT u FROM Userdata u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "Userdata.findByPassword", query = "SELECT u FROM Userdata u WHERE u.password = :password")
    , @NamedQuery(name = "Userdata.findByEmail", query = "SELECT u FROM Userdata u WHERE u.email = :email")
    , @NamedQuery(name = "Userdata.findByUsername", query = "SELECT u FROM Userdata u WHERE u.username = :username")
    , @NamedQuery(name = "Userdata.findByToken", query = "SELECT u FROM Userdata u WHERE u.token = :token")
    , @NamedQuery(name = "Userdata.findbyLoginPassword", query = "SELECT u FROM Userdata u WHERE u.username = :username AND u.password = :password")})


@SqlResultSetMapping(name="UserResult", classes = {
@ConstructorResult(targetClass = Userdata.class, 
  columns = {
@ColumnResult(name="id"),
@ColumnResult(name="username"), 
@ColumnResult(name="password")})})




@NamedNativeQueries({
    @NamedNativeQuery(name = "Userdatalogin", query = "SELECT log.id, log.username, log.password FROM userdata log WHERE log.username = :username", resultSetMapping = "UserResult")
})


public class Userdata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 45)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 128)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 128)
    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version;
    @Column(name = "token")
    private String token;
    @Size(max=256)

    public Userdata() {
    }
    
    
    //for a NamedNativeQuery a constructor with the exact same attributes is necessary for hibernate to create the Object from the database
    public Userdata(Integer id, String username, String password){
        
    this.id = id;
    this.username = username;
    this.password = password;
    }
    
    
    
    public Userdata(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        if (!(object instanceof Userdata)) {
            return false;
        }
        Userdata other = (Userdata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.flopewsserver.entities.Userdata[ id=" + id + " ]";
    }
    
}
