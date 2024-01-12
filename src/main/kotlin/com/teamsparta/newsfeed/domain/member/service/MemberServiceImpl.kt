package com.teamsparta.newsfeed.domain.member.service

import com.teamsparta.newsfeed.domain.exception.InvalidCredentialException
import com.teamsparta.newsfeed.domain.exception.MemberNotFoundException
import com.teamsparta.newsfeed.domain.member.dto.LoginRequest
import com.teamsparta.newsfeed.domain.member.dto.LoginResponse
import com.teamsparta.newsfeed.domain.member.dto.MemberResponse
import com.teamsparta.newsfeed.domain.member.dto.SignUpRequest
import com.teamsparta.newsfeed.domain.member.model.Member
import com.teamsparta.newsfeed.domain.member.model.MemberRole
import com.teamsparta.newsfeed.domain.member.model.toResponse
import com.teamsparta.newsfeed.domain.member.repository.MemberRepository
import com.teamsparta.newsfeed.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
        private val memberRepository: MemberRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtPlugin: JwtPlugin
) : MemberService {
    @Transactional
    override fun signUp(request: SignUpRequest): MemberResponse {
        when {
            memberRepository.existsByEmail(request.email) -> {
                throw IllegalStateException("Email is already in use")
            }
        }
        return memberRepository.save(
                Member(
                        email = request.email,
                        password = passwordEncoder.encode(request.password),
                        name = request.name,
                        tmi = request.tmi,
                        role = when (request.role) {
                            "ADMIN" -> MemberRole.ADMIN
                            "MEMBER" -> MemberRole.MEMBER
                            else -> throw IllegalArgumentException("Invalid role")
                        }
                )
        ).toResponse()
    }
    override fun login(request: LoginRequest): LoginResponse {
        val member = memberRepository.findByEmail(request.email)
                ?: throw MemberNotFoundException(null)
        if (member.role.name != request.role || !passwordEncoder.matches(request.password, member.password)) {
            throw InvalidCredentialException()
        }
        return LoginResponse(
                accessToken = jwtPlugin.generateAccessToken(
                        subject = member.id.toString(),
                        email = member.email,
                        role = member.role.name
                )
        )
    }
    override fun searchMyInfo(id: Long): MemberResponse {
        val member = memberRepository.findByIdOrNull(id) ?: throw MemberNotFoundException(id)
        return member.toResponse()
    }
}