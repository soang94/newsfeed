package com.teamsparta.newsfeed.domain.article.dto

import java.sql.Timestamp

data class UpdateArticleRequest(
    val title: String,
    val tag: String,
    val summary: String,
    val content: String,
    val date: Timestamp,
    val name: String,
)