package com.teamsparta.newsfeed.domain.article.service

import com.teamsparta.newsfeed.domain.article.dto.ArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.CreateArticleRequest
import com.teamsparta.newsfeed.domain.article.dto.UpdateArticleRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ArticleService {
    fun getArticleList(pageable: Pageable): Page<ArticleResponse>

    fun getArticleById(articleId: Long): ArticleResponse

    fun createArticle(request: CreateArticleRequest): ArticleResponse

    fun updateArticle(articleId: Long, request: UpdateArticleRequest): ArticleResponse

    fun deleteArticle(articleId: Long)
}