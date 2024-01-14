package com.teamsparta.newsfeed.domain.comment.dto

data class CommentResponse(
        val id: Long?,
        val comment: String,
        val name: String,
)
