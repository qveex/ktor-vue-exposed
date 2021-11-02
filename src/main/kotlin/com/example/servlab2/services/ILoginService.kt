package com.example.servlab2.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


interface ILoginService {

    fun loadUserByUsername(login: String): UserDetails?
}