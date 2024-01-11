package com.teamsparta.newsfeed.domain.comment.dto
import com.teamsparta.newsfeed.domain.comment.model.Comment
import java.util.*
data class CommentResponse(
    val id: Long?,
    val comment: String,
    val date: Date,
    val name: String,
//    val articleId: Long
)
//{
//    companion object {
//        fun from(comment: Comment): CommentResponse {
//            return CommentResponse(
//                id = comment.id,
//                comment = comment.comment,
//                date = comment.date,
//                name = comment.name,
////                articleId = comment.article.id() ?: throw Exception("target article is not persisted"),
//            )
//        }
//    }
//}