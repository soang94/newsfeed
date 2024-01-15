package com.teamsparta.newsfeed.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BasePostingEntity : BaseTimeEntity() {
    @CreatedBy
    @Column(updatable = false)
    protected var createdBy : String? =null

    @LastModifiedBy
    protected var lastModifiedBy: String? =null
}
