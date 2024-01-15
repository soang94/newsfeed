package com.teamsparta.newsfeed.domain.comment.dto

data class DeleteCommentRequest(
        val name: String,
        val id: Long,
)