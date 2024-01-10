package com.teamsparta.newsfeed.domain.article.service

import com.teamsparta.newsfeed.domain.article.dto.ArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.CreateArticleRequest
import com.teamsparta.newsfeed.domain.article.dto.RetrieveArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.RetrieveArticleResponse.Companion.from
import com.teamsparta.newsfeed.domain.article.dto.UpdateArticleRequest
import com.teamsparta.newsfeed.domain.article.model.Article
import com.teamsparta.newsfeed.domain.article.model.toResponse
import com.teamsparta.newsfeed.domain.article.repository.ArticleRepository
import com.teamsparta.newsfeed.domain.exception.ArticleNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository
): ArticleService {
    override fun getArticleList(): List<ArticleResponse> {
        return articleRepository.findAll().map {it.toResponse()}
    }

    override fun getArticleById(articleId: Long): RetrieveArticleResponse {
        val getArticle = articleRepository.findByIdOrNull(articleId) ?: throw ArticleNotFoundException("Article", articleId)

        return getArticle.let { RetrieveArticleResponse.from(it) }
    }

    @Transactional
    override fun createArticle(request: CreateArticleRequest): ArticleResponse {
        return articleRepository.save<Article?>(
            Article(
                title = request.title,
                summary = request.summary,
                tag = request.tag,
                content = request.content,
                date = request.date,
                name = request.name
            )
        ).toResponse()
    }

    @Transactional
    override fun updateArticle(articleId: Long, request: UpdateArticleRequest): ArticleResponse {
        val updateArticle = articleRepository.findByIdOrNull(articleId) ?: throw ArticleNotFoundException("Article", articleId)
        val (title, summary, tag, content, date, name) = request

        updateArticle.title = title
        updateArticle.summary = summary
        updateArticle.tag = tag
        updateArticle.content = content
        updateArticle.date = date
        updateArticle.name =name

        return articleRepository.save(updateArticle).toResponse()
    }

    @Transactional
    override fun deleteArticle(articleId: Long) {
        val deleteArticle = articleRepository.findByIdOrNull(articleId) ?: throw ArticleNotFoundException("Article", articleId)
        articleRepository.delete(deleteArticle)
    }

}