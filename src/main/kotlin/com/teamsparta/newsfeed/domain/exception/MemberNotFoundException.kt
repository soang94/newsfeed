package com.teamsparta.newsfeed.domain.exception

data class MemberNotFoundException(val id : Long?) : RuntimeException(
        "존재하지 않는 회원입니다."
)

