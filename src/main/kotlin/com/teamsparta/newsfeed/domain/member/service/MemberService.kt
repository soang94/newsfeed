package com.teamsparta.newsfeed.domain.member.service

import com.teamsparta.newsfeed.domain.member.dto.MemberResponse
import com.teamsparta.newsfeed.domain.member.dto.ProfileResponse
import com.teamsparta.newsfeed.domain.member.dto.SignUpRequest
import com.teamsparta.newsfeed.domain.member.dto.UpdateProfileRequest

interface MemberService {

    fun signUp(signUpRequest: SignUpRequest): MemberResponse

    fun updateProfile(memberId: Long,
                      updateProfileRequest: UpdateProfileRequest): ProfileResponse

    fun getProfile(memberId: Long): ProfileResponse

}