package com.example.servlab2.services

import com.example.servlab2.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class LoginService: ILoginService {

    @Autowired private lateinit var userRepo: UserRepo

    override fun loadUserByUsername(login: String): UserDetails {
        val lg = userRepo.findByLogin(login)
        if (lg == null) throw UsernameNotFoundException("user not found")
        return MyLogin(lg)
    }
}