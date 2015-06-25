package org.example.service

import org.example.domain.Address

public class PlayWithAddress {

    fun findAll(): MutableList<Address>? {
        return Address.findList();
    }

}