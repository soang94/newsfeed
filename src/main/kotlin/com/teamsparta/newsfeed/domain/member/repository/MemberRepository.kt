package com.teamsparta.newsfeed.domain.member.repository

import com.teamsparta.newsfeed.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Member?

}