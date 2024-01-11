package com.teamsparta.newsfeed.domain.article.dto

import java.util.Date


data class ArticleResponse(
    val id: Long?,
    val title: String,
    val tag: String,
    val summary: String,
    val content: String,
    val date: Date,
    val name: String,
)
