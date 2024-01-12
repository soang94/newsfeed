package com.teamsparta.newsfeed.domain.member.dto

data class SignUpRequest(
        val email: String,
        val password: String,
        val name: String,
        val tmi: String,
        val role: String
)