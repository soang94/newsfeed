package com.teamsparta.newsfeed.domain.comment.dto

import java.util.*

data class CreateCommentRequest(
        val comment: String,
        val name: String,
        val date: Date,
//    val articleId: Long,
)