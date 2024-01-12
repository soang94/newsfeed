package com.teamsparta.newsfeed.domain.member.model

import com.teamsparta.newsfeed.domain.member.dto.MemberResponse
import com.teamsparta.newsfeed.domain.member.dto.ProfileResponse
import com.teamsparta.newsfeed.domain.member.dto.UpdateProfileRequest
import jakarta.persistence.*

@Entity
@Table(name = "members")
class Member(

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "tmi", nullable = true)
    var tmi: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: ROLE = ROLE.USER

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun toResponse(): MemberResponse {
        return MemberResponse(
            id = id!!,
            email = email,
            password = password,
            name = name,
            tmi = tmi
        )
    }

    fun toProfileResponse(): ProfileResponse {
        return ProfileResponse(email, name, tmi)
    }

    fun updateProfile(updateProfileRequest: UpdateProfileRequest) {
        email = updateProfileRequest.email
        password = updateProfileRequest.password
        name = updateProfileRequest.name
        tmi = updateProfileRequest.tmi
    }
}