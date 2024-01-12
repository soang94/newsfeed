package com.teamsparta.newsfeed.infra.security.jwt

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class MemberPrincipal(
        val id: Long,
        val email: String,
        val authorities: Collection<GrantedAuthority>
) {
    constructor(id: Long, email: String, roles: Set<String>) : this(
            id,
            email,
            roles.map { SimpleGrantedAuthority("ROLE_$it") })

}