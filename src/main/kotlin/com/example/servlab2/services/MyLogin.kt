package com.example.servlab2.services

import com.example.servlab2.entities.UserEntity
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User

class MyLogin(user: UserEntity) : User(user.login, user.hash, AuthorityUtils.createAuthorityList("ALL"))