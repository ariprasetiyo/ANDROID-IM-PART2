/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ari.prasetiyo.chat.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author bsp-it-01
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class users {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;

    @Column(nullable = false, length = 150)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    String name;

    @Column(nullable = false, length = 150)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    String address;
    
    @Column(nullable = false, length = 150)
    @NotNull
    @NotEmpty
    String job;
    
    @Column(nullable = false, unique = true, length = 150)
    @Email
    String email;

    @Column(nullable = false, length = 255)
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 255)
    String password;
    
    @Column(name = "user_key")
    String useKey;
    
    @Column(name = "ip_login_last", length = 25)
    String ipLoginLast;

    @Column(length = 50, nullable = false)
    String room;

    @Column(length = 5)
    int port;

    @Column(length = 1)
    int active;

    @Temporal(TemporalType.DATE)
    @Column(name = "join_date")
    @NotNull
    Date joinDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseKey() {
        return useKey;
    }

    public void setUseKey(String useKey) {
        this.useKey = useKey;
    }

    public String getIpLoginLast() {
        return ipLoginLast;
    }

    public void setIpLoginLast(String ipLoginLast) {
        this.ipLoginLast = ipLoginLast;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

}
