package com.teamsparta.newsfeed.domain.member.controller

import com.teamsparta.newsfeed.domain.member.dto.LoginRequest
import com.teamsparta.newsfeed.domain.member.dto.MemberResponse
import com.teamsparta.newsfeed.domain.member.dto.SignUpRequest
import com.teamsparta.newsfeed.domain.member.dto.UpdateProfileRequest
import com.teamsparta.newsfeed.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/members")
@RestController
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("회원가입에 성공하셨습니다.")
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("로그인되었습니다.")
    }

    @GetMapping("/{memberId}")
    fun getMemberProfile(@PathVariable memberId: Long,
                         @RequestBody getProfileRequest: UpdateProfileRequest
    )
//    : ResponseEntity<MemberResponse>
    {
    }

    @PutMapping("/{memberId}/profile")
    fun updateMemberProfile(@PathVariable memberId: Long,
                            @RequestBody updateProfileRequest: UpdateProfileRequest
    ): ResponseEntity<MemberResponse> {
        TODO()
    }


}