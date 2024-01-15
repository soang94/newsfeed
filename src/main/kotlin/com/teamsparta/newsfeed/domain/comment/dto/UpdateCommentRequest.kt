package com.teamsparta.newsfeed.domain.comment.dto

data class UpdateCommentRequest(
        val comment: String,
        val name: String,
)