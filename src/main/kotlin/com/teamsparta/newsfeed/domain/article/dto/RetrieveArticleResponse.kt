package com.teamsparta.newsfeed.domain.article.dto

import com.teamsparta.newsfeed.domain.comment.dto.CommentResponse
import java.util.*

data class RetrieveArticleResponse(
    val id: Long?,
    val title: String,
    val content: String,
    val summary: String,
    val date: Date,
    val name: String,
    val comments: List<CommentResponse>
)
