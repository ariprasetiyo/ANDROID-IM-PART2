/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ari.prasetiyo.chat.service.controller;

import ari.prasetiyo.chat.service.dao.signUpDao;
import ari.prasetiyo.chat.service.entity.users;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bsp-it-01
 */
@RestController
@RequestMapping("/api")
public class signUp {
    
    @Autowired
    private signUpDao signUpDao;
    
    @RequestMapping(value ="/signup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMateri(@RequestBody @Valid users u){
        u.setPort(0);
        u.setActive(0);
        signUpDao.save(u);
    }

    
    //Test
    @RequestMapping(value = "/list", method = RequestMethod.GET )
    public Page<users> test ( Pageable page){
        return signUpDao.findAll(page);
    }

}
