package com.teamsparta.newsfeed.domain.article.model

import com.teamsparta.newsfeed.domain.article.dto.ArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.CreateArticleRequest
import com.teamsparta.newsfeed.domain.article.dto.UpdateArticleRequest
import com.teamsparta.newsfeed.domain.comment.model.Comment
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "articles")
class Article(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "summary", nullable = false)
    var summary: String,

    @Column(name = "tag", nullable = false)
    var tag: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "date", nullable = false)
    var date: Date,

    @Column(name = "name", nullable = false)
    var name: String,

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun article(request: CreateArticleRequest) = Article(
        title = request.title,
        summary = request.summary,
        tag = request.tag,
        content = request.content,
        date = request.date,
        name = request.name
    )
    fun toUpdate(request: UpdateArticleRequest) {
        title = request.title
        summary = request.summary
        tag = request.tag
        content = request.content
        date = request.date
        name = request.name
    }
}
fun Article.toResponse(): ArticleResponse {
    return ArticleResponse(
        id = id!!,
        title = title,
        summary = summary,
        tag = tag,
        content = content,
        date = date,
        name = name,
    )
}
