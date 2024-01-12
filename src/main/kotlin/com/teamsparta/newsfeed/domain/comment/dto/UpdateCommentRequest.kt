package com.teamsparta.newsfeed.domain.comment.dto

data class UpdateCommentRequest(
        val id: Long,
        val comment: String,
        val name: String,
)