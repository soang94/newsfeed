package com.teamsparta.newsfeed.domain.article.controller

import com.teamsparta.newsfeed.domain.article.dto.ArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.CreateArticleRequest
import com.teamsparta.newsfeed.domain.article.dto.UpdateArticleRequest
import com.teamsparta.newsfeed.domain.article.service.ArticleService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/article")
@RestController
class ArticleController(
        private val articleService: ArticleService
) {
    @Operation(summary = "article 전제 조회")
    @GetMapping
    fun getArticleList(
            @PageableDefault(size = 3, sort = ["name"], direction = Sort.Direction.DESC)
            pageable: Pageable,
    ): ResponseEntity<Page<ArticleResponse>> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.getArticleList(pageable))
    }

    @Operation(summary = "article 단건 조회")
    @GetMapping("/{articleId}")
    fun getArticle(
            @PathVariable articleId: Long
    ): ResponseEntity<ArticleResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.getArticleById(articleId))
    }

    @Operation(summary = "article 생성")
    @PostMapping
    fun createArticle(
            @RequestBody createArticleRequest: CreateArticleRequest
    ): ResponseEntity<ArticleResponse> {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.createArticle(createArticleRequest))
    }

    @Operation(summary = "article 수정")
    @PutMapping("/{articleId}")
    fun updateArticle(
            @PathVariable articleId: Long,
            @RequestBody updateArticleRequest: UpdateArticleRequest
    ): ResponseEntity<ArticleResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.updateArticle(articleId, updateArticleRequest))
    }

    @Operation(summary = "article 삭제")
    @DeleteMapping("/{articleId}")
    fun deleteArticle(
            @PathVariable articleId: Long,
    ): ResponseEntity<Unit> {
        articleService.deleteArticle(articleId)
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
    }
}