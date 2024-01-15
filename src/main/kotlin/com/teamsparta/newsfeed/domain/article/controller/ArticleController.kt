package com.teamsparta.newsfeed.domain.article.controller

import com.teamsparta.newsfeed.domain.article.dto.ArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.CreateArticleRequest
import com.teamsparta.newsfeed.domain.article.dto.RetrieveArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.UpdateArticleRequest
import com.teamsparta.newsfeed.domain.article.service.ArticleService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("/articles")
@RestController
class ArticleController(
        private val articleService: ArticleService
) {
    @Operation(summary = "article 전체 조회")
    @GetMapping
    fun getArticleList(
        @PageableDefault(size = 4, sort = ["name"], direction = Sort.Direction.DESC)
        pageable: Pageable
    ): ResponseEntity<Page<ArticleResponse>> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.getArticleList(pageable))
    }

    @Operation(summary = "article 단건 조회")
    @GetMapping("/{articleId}")
    fun getArticle(
            @PathVariable articleId: Long
    ): ResponseEntity<RetrieveArticleResponse?> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.getArticleById(articleId))
    }

    @Operation(summary = "article 생성")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun createArticle(
            @RequestBody createArticleRequest: CreateArticleRequest
    ): ResponseEntity<ArticleResponse> {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.createArticle(createArticleRequest))
    }

    @Operation(summary = "article 수정")
    @PutMapping("/{articleId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun deleteArticle(
            @PathVariable articleId: Long,
    ): ResponseEntity<Any> {
        articleService.deleteArticle(articleId)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("선택하신 기사가 삭제되었습니다.")
    }
}