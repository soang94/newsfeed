package com.teamsparta.newsfeed.domain.comment.model

import com.teamsparta.newsfeed.domain.article.model.Article
import com.teamsparta.newsfeed.domain.comment.dto.CommentResponse
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "comments")
class Comment(
    @Column
    var comment: String,
    @Column
    var name: String,
    @Column
    val date: Date,

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "articleId")
    val article: Article

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun checkAuthentication(name: String) {
        if (name != this.name) {
            throw Exception("wrong authentication for comment")
        }
    }
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        comment = comment,
        name = name,
        date = date,
//        articleId = article.id ?: throw Exception("target article is not persisted"),
    )
}