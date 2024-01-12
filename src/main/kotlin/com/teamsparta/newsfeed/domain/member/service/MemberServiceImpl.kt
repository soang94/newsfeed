package com.teamsparta.newsfeed.domain.member.service

import com.teamsparta.newsfeed.domain.exception.MemberNotFoundException
import com.teamsparta.newsfeed.domain.member.dto.LoginRequest
import com.teamsparta.newsfeed.domain.member.dto.LoginResponse
import com.teamsparta.newsfeed.domain.member.dto.MemberResponse
import com.teamsparta.newsfeed.domain.member.dto.SignUpRequest
import com.teamsparta.newsfeed.domain.member.model.Member
import com.teamsparta.newsfeed.domain.member.model.MemberRole
import com.teamsparta.newsfeed.domain.member.model.toResponse
import com.teamsparta.newsfeed.domain.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull

class MemberServiceImpl (
        private val memberRepository: MemberRepository
) : MemberService {
    override fun signUp(request: SignUpRequest): MemberResponse {
        when {
            memberRepository.existsByEmail(request.email) -> {
                throw IllegalStateException("Email is already in use")
            }
        }
        // 기능 : DB에 정보 저장 & 일부 데이터 Response로 반환
        return memberRepository.save(
                Member(
                        // DB에 저장할 데이터들
                        email = request.email,
                        // 비밀번호 암호화
                        password = request.password,
                        name = request.name,
                        tmi = request.tmi,
                        role = when (request.role) {
                            "ADMIN" -> MemberRole.ADMIN
                            "MEMBER" -> MemberRole.MEMBER
                            else -> throw IllegalArgumentException("Invalid role")
                        }
                )
        ).toResponse() //response로 반환할 데이터들
    }

    override fun login(request: LoginRequest): LoginResponse {
        val member = memberRepository.findByEmail(request.email) ?: throw  MemberNotFoundException(null)// MemberNotFoundException
        TODO("토큰 검증하기 / 로그인 리스폰스")
    }

    override fun searchMyInfo(id: Long): MemberResponse {
        val member = memberRepository.findByIdOrNull(id)?:throw MemberNotFoundException(id)
        return member.toResponse()
    }
}