package com.teamsparta.newsfeed.domain.article.repository

import com.teamsparta.newsfeed.domain.article.model.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long> {
}