package com.teamsparta.newsfeed.domain.article.dto

data class ArticleResponse(
        val id: Long?,
        val title: String,
        val tag: String,
        val summary: String,
        val content: String,
        val name: String,
)
