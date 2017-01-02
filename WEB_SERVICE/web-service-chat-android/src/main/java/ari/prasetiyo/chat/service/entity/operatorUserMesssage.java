/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ari.prasetiyo.chat.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *Created when user have been joined on room
 * @author bsp-it-01
 */
@Entity(name = "operator_user_messagae")
public class operatorUserMesssage {
    
    @Column(name = "id_operator", nullable = true)
    String idOperator;

    @Id
    @Column(name = "id_user", nullable = false )
    String idUser;

    public String getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
