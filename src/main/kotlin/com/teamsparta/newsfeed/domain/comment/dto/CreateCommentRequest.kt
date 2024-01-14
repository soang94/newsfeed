package com.teamsparta.newsfeed.domain.comment.dto


data class CreateCommentRequest(
        val comment: String,
        val name: String,
)