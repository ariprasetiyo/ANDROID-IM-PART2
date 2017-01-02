/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ari.prasetiyo.chat.service.entity;

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
@Entity
public class operator {

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
    @NotNull
    @Email
    @NotEmpty
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
    
}
