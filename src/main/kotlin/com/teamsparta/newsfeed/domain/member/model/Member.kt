package com.teamsparta.newsfeed.domain.member.model

import com.teamsparta.newsfeed.domain.BaseTimeEntity

import com.teamsparta.newsfeed.domain.member.dto.MemberResponse
import jakarta.persistence.*


@Entity
@Table(name = "members")
class Member(
        @Column(name = "email")
        var email: String,
        @Column(name = "password")
        var password: String,
        @Column(name = "name")
        val name: String,
        @Column(name = "tmi")
        val tmi: String,
        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        val role: MemberRole,
        ) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}

fun Member.toResponse(): MemberResponse {
    return MemberResponse(
            id = id!!,
            email = email,
            name = name,
            tmi = tmi,
            role = role.name
    )
}