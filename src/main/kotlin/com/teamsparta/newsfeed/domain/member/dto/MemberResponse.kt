package com.teamsparta.newsfeed.domain.member.dto

data class MemberResponse(
        val id: Long,
        val email: String,
        val name: String,
        val tmi: String,
        val role: String
)