package com.teamsparta.newsfeed.domain.member.service

import com.teamsparta.newsfeed.domain.exception.ModelNotFoundException
import com.teamsparta.newsfeed.domain.member.dto.MemberResponse
import com.teamsparta.newsfeed.domain.member.dto.ProfileResponse
import com.teamsparta.newsfeed.domain.member.dto.SignUpRequest
import com.teamsparta.newsfeed.domain.member.dto.UpdateProfileRequest
import com.teamsparta.newsfeed.domain.member.model.Member
import com.teamsparta.newsfeed.domain.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder, // 주입!
): MemberService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): MemberResponse {
        if (memberRepository.existsByEmail(signUpRequest.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return memberRepository.save(
            Member(
                email = signUpRequest.email,
                password = passwordEncoder.encode(signUpRequest.password), // 암호화!
                name = signUpRequest.name,
                tmi = signUpRequest.tmi
            )
        ).toResponse()
    }

    override fun getProfile(memberId: Long): ProfileResponse {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw IllegalStateException("Profile is not found.")

        return member.toProfileResponse()
    }

    @Transactional
    override fun updateProfile(memberId: Long, updateProfileRequest: UpdateProfileRequest): ProfileResponse {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member", memberId)
        member.updateProfile(updateProfileRequest)

        // update 시에 save 안해도 flush 시 update query 실행됨
        return member.toProfileResponse()
    }
}