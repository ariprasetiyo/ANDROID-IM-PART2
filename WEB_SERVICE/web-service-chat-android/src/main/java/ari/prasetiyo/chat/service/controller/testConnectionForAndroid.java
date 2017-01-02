package ari.prasetiyo.chat.service.controller;

import ari.prasetiyo.chat.service.dao.signUpDao;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class testConnectionForAndroid {
    
    @Autowired
    private signUpDao signUpDao;
     
    @RequestMapping(value = "/testConnection", method = RequestMethod.GET )
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object>  testConnection(){
        Map<String, Object> putInHere = new HashMap<>();
        putInHere.put("testConnection", 1);
        return putInHere;
    }
}
