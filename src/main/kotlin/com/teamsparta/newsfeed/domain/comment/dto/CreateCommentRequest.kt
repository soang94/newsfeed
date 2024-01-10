package com.teamsparta.newsfeed.domain.comment.dto

import java.util.Date

data class CreateCommentRequest(
    val comment: String,
    val name: String,
    val date: Date,
    val articleId: Long,
)