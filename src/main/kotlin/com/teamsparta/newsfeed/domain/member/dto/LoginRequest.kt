package com.teamsparta.newsfeed.domain.member.dto

data class LoginRequest(
    val email: String,
    val password: String
)