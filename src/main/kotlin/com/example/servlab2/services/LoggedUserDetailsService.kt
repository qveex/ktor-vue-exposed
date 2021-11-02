package com.example.servlab2.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class LoggedUserDetailsService(@Autowired private val userService: ILoginService) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        return userService.loadUserByUsername(login) ?: throw UsernameNotFoundException("Invalid user $login")
    }
}
