package com.teamsparta.newsfeed.domain.comment.dto

import java.util.*

data class UpdateCommentRequest(
    val id: Long?,
    val comment: String,
    val name: String,
)