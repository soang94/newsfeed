package com.teamsparta.newsfeed.domain.article.service

import com.teamsparta.newsfeed.domain.article.dto.ArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.CreateArticleRequest
import com.teamsparta.newsfeed.domain.article.dto.RetrieveArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.UpdateArticleRequest

interface ArticleService {
    fun getArticleList(): List<ArticleResponse>

    fun getArticleById(articleId: Long): RetrieveArticleResponse

    fun createArticle(request: CreateArticleRequest): ArticleResponse

    fun updateArticle(articleId: Long, request: UpdateArticleRequest): ArticleResponse

    fun deleteArticle(articleId: Long)
}