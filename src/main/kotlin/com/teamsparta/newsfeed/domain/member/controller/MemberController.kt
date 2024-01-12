package com.teamsparta.newsfeed.domain.member.controller

import com.teamsparta.newsfeed.domain.member.dto.LoginRequest
import com.teamsparta.newsfeed.domain.member.dto.LoginResponse
import com.teamsparta.newsfeed.domain.member.dto.MemberResponse
import com.teamsparta.newsfeed.domain.member.dto.SignUpRequest
import com.teamsparta.newsfeed.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

class MemberController(
        private val memberService: MemberService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<MemberResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.signUp(signUpRequest))
    }

    @PostMapping("/login")
    fun signIn(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.login(loginRequest))
    }

    @GetMapping("/info/{id}")
    fun searchMyInfo(@PathVariable id: Long): ResponseEntity<MemberResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.searchMyInfo(id))
    }

}