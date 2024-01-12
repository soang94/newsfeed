package com.teamsparta.newsfeed.domain.member.dto

data class UpdateProfileRequest(
    val email: String,
    val password: String,
    val name: String,
    val tmi: String
)
