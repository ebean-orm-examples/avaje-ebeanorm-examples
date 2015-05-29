package org.example.service

import org.example.domain.Address

/**
 * Created by rob on 29/05/15.
 */
public class PlayWithAddress {


    fun findAll(): MutableList<Address>? {
        return Address.findList();
    }

}