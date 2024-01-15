package com.teamsparta.newsfeed.domain.member.dto

import jakarta.validation.constraints.Size

data class SignUpRequest(
        val email: String,
        @field: Size(min=3, max = 20, message = "비밀번호는 3자 이상 20자 이하여야 합니다.")
        val password: String,
        val name: String,
        val tmi: String,
        val role: String
)