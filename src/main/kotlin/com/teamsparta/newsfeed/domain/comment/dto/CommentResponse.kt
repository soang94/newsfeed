package com.teamsparta.newsfeed.domain.comment.dto
import java.util.*
data class CommentResponse(
    val id: Long?,
    val comment: String,
    val date: Date,
    val name: String,
)
