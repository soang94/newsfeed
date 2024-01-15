package com.teamsparta.newsfeed.domain.comment.service

import com.teamsparta.newsfeed.domain.article.repository.ArticleRepository
import com.teamsparta.newsfeed.domain.comment.dto.CommentResponse
import com.teamsparta.newsfeed.domain.comment.dto.CreateCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.newsfeed.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.newsfeed.domain.comment.model.Comment
import com.teamsparta.newsfeed.domain.comment.model.toResponse
import com.teamsparta.newsfeed.domain.comment.repository.CommentRepository
import com.teamsparta.newsfeed.domain.exception.CommentNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
        val articleRepository: ArticleRepository,
        val commentRepository: CommentRepository
) : CommentService {

    @Transactional
    override fun createComment(request: CreateCommentRequest, articleId: Long): CommentResponse {
        val targetArticle = articleRepository.findByIdOrNull(articleId)
                ?: throw Exception("target article is not found")
        val comment = Comment(
                article = targetArticle,
                comment = request.comment,
                name = request.name,
        )
        commentRepository.save(comment)

        return comment.toResponse()
    }

    @Transactional
    override fun updateComment(articleId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val foundComment = commentRepository.findByIdOrNull(commentId)
                ?: throw CommentNotFoundException(commentId, request.name)

        foundComment.checkAuthentication(request.name)
        foundComment.comment = request.comment

        return foundComment.toResponse()
    }

    @Transactional
    override fun deleteComment(request: DeleteCommentRequest) {
        val foundComment = request.id.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw Exception("target comment is not found")

        foundComment.checkAuthentication(request.name)

        commentRepository.deleteById(request.id)
    }

}