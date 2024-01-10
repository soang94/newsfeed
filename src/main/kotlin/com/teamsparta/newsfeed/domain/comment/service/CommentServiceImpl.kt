package com.teamsparta.newsfeed.domain.comment.service

import com.teamsparta.newsfeed.domain.article.repository.ArticleRepository
import com.teamsparta.newsfeed.domain.comment.dto.CommentResponse
import com.teamsparta.newsfeed.domain.comment.dto.CreateCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.newsfeed.domain.comment.model.Comment
import com.teamsparta.newsfeed.domain.comment.model.toResponse
import com.teamsparta.newsfeed.domain.comment.repository.CommentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    val articleRepository: ArticleRepository,
    val commentRepository: CommentRepository
): CommentService {

    override fun createComment(request: CreateCommentRequest, articleId: Long): CommentResponse {
        val targetArticle = articleRepository.findByIdOrNull(articleId)
            ?: throw Exception("target article is not found")

        val comment = Comment(
            article = targetArticle,
            comment = request.comment,
            name = request.name,
            date = request.date,
        )
        commentRepository.save(comment)

        return comment.toResponse()
    }

    override fun updateComment(request: UpdateCommentRequest): CommentResponse {
        val foundComment = request.id?.
        let {commentRepository.findByIdOrNull(it)} ?: throw Exception("target comment is not found")

        foundComment.checkAuthentication(request.name)
        foundComment.comment = request.comment

        commentRepository.save(foundComment)

        return foundComment.toResponse()
    }

    override fun deleteComment(request: DeleteCommentRequest): Unit {
        val foundComment = request.id.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw Exception("target comment is not found")

        foundComment.checkAuthentication(request.name)

        commentRepository.deleteById(request.id)
    }

}