package com.teamsparta.newsfeed.domain.article.dto

import com.teamsparta.newsfeed.domain.article.model.Article
import com.teamsparta.newsfeed.domain.comment.dto.CommentResponse
import com.teamsparta.newsfeed.domain.comment.model.toResponse

data class RetrieveArticleResponse(
        val id: Long?,
        val title: String,
        val content: String,
        val summary: String,
        val name: String,
        val comments: List<CommentResponse>,
) {
    companion object {
        fun from(article: Article): RetrieveArticleResponse {
            return RetrieveArticleResponse(
                    id = article.id!!,
                    title = article.title,
                    content = article.content,
                    summary = article.summary,
                    name = article.name,
                    comments = article.comments.map { it.toResponse() }
            )
        }
    }
}
