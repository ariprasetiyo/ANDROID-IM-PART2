/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ari.prasetiyo.chat.service.dao;

import ari.prasetiyo.chat.service.entity.users;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author bsp-it-01
 */
public interface userDao extends PagingAndSortingRepository<users, String> {
    
}
