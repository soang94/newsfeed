package com.teamsparta.newsfeed.domain.member.service

import com.teamsparta.newsfeed.domain.member.dto.LoginRequest
import com.teamsparta.newsfeed.domain.member.dto.LoginResponse
import com.teamsparta.newsfeed.domain.member.dto.MemberResponse
import com.teamsparta.newsfeed.domain.member.dto.SignUpRequest
import com.teamsparta.newsfeed.domain.member.model.Member

interface MemberService {
    fun signUp(request: SignUpRequest): MemberResponse
    fun login(request: LoginRequest) : LoginResponse
    fun searchMyInfo(id: Long): MemberResponse
}